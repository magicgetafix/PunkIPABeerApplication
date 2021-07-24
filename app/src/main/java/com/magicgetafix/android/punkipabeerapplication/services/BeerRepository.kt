package com.magicgetafix.android.punkipabeerapplication.services

import androidx.room.Database
import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import com.magicgetafix.android.punkipabeerapplication.database.BeerDatabase
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel
import com.moneypenny.telephoneanswering.schedulers.ISchedulers
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.rxjava3.disposables.Disposable


class BeerRepository constructor(private val databaseProvider: IDatabaseProvider, private val apiFactory: IApiFactory, private val schedulers: ISchedulers): IBeerRepository {

    private val requestSubscription: Disposable? = null

    override fun getBeers(): Flowable<List<BeerViewModel>>{

        return Flowable.create<List<BeerViewModel>>({
                emitter -> try {

                    databaseProvider.getDatabase().beerDao().getAllBeers().forEach{
                        val list: ArrayList<BeerViewModel> = arrayListOf()
                        it.forEach {
                            val beer = BeerViewModel(it.id, it.name, it.imageUrl, it.strength, it.tagline, it.description, it.foodPairingNotes)
                            //todo add validation
                            if (beer != null){
                                list.add(beer)
                            }
                        }
                        emitter.onNext(list)
                    }



                }
        catch (e: Exception){
            emitter.onError(e)
        }

        }, BackpressureStrategy.BUFFER)

    }

    private fun requestBeer() {
        requestSubscription?.dispose()
        /*requestSubscription =
            apiFactory.getBeerApi().getAllBeers().subscribeOn(schedulers.background)
                .observeOn(schedulers.ui).subscribe(
                onNext = { print(mess) }, onError = (), onComplete = ()
            )*/

    }
}

interface IBeerRepository{

    fun getBeers(): Flowable<List<BeerViewModel>>
}