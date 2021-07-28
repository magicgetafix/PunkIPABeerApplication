package com.magicgetafix.android.punkipabeerapplication.services

import android.renderscript.RenderScript
import com.magicgetafix.android.punkipabeer.application.utils.Mapper
import com.magicgetafix.android.punkipabeerapplication.database.models.BeerDbModel
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel
import com.magicgetafix.android.punkipabeerapplication.utils.DataValidator
import com.moneypenny.telephoneanswering.schedulers.ISchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber


class BeerRepository constructor(private val databaseProvider: IDatabaseProvider, private val apiFactory: IApiFactory, private val schedulers: ISchedulers): IBeerRepository {

    private var requestSubscription: Disposable? = null

    override fun getBeers(): Flowable<List<BeerViewModel>> {

        requestBeers()
        return Flowable.create<List<BeerViewModel>>({
                emitter -> try {

                    databaseProvider.getDatabase().beerDao().getAllBeers().forEach{
                        val list: ArrayList<BeerViewModel> = arrayListOf()
                        it.forEach {
                            val beer = Mapper.toViewModel(it)
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

    override fun getStrongBeers(): Flowable<List<BeerViewModel>> {
        return Flowable.create<List<BeerViewModel>>({
                emitter -> try {

            databaseProvider.getDatabase().beerDao().getStrongBeers().forEach{
                val list: ArrayList<BeerViewModel> = arrayListOf()
                it.forEach {
                    val beer = Mapper.toViewModel(it)
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

    override fun getGermanBeers(): Flowable<List<BeerViewModel>> {
        return Flowable.create<List<BeerViewModel>>({
                emitter -> try {

            databaseProvider.getDatabase().beerDao().getGermanBeers().forEach{
                val list: ArrayList<BeerViewModel> = arrayListOf()
                it.forEach {
                    val beer = Mapper.toViewModel(it)
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

    override fun getBelgianBeers(): Flowable<List<BeerViewModel>> {
        return Flowable.create<List<BeerViewModel>>({
                emitter -> try {

            databaseProvider.getDatabase().beerDao().getBelgianBeers().forEach{
                val list: ArrayList<BeerViewModel> = arrayListOf()
                it.forEach {
                    val beer = Mapper.toViewModel(it)
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

    override fun getEuropeanBeers(): Flowable<List<BeerViewModel>> {

        return Flowable.create<List<BeerViewModel>>({
                emitter -> try {

            databaseProvider.getDatabase().beerDao().getAllEuropeanBeers().forEach{
                val list: ArrayList<BeerViewModel> = arrayListOf()
                it.forEach {
                    val beer = Mapper.toViewModel(it)
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

   private fun requestBeers() {
        requestSubscription?.dispose()
        val list: ArrayList<BeerDbModel> = arrayListOf()
        requestSubscription = apiFactory.getBeerApi()
            .getAllBeers()
            .subscribeOn(schedulers.background)
            .observeOn(schedulers.ui)
            .subscribe({
                 it.forEach {
                     if (DataValidator.isValidBeer(it)){
                         val beerDbModel = Mapper.toDbModel(it)
                         list.add(beerDbModel)
                        }
                     }

                    if (!list.isEmpty()){
                        insertBeersIntoDatabase(list)
                    }
                    },{
                        Timber.wtf("Api Error :%s", it.message)
                    })


    }

    override fun insertBeersIntoDatabase(list: List<BeerDbModel>){
        try {
            GlobalScope.launch {
                databaseProvider.getDatabase().beerDao().insertBeers(list)
            }
        }
        catch (e: Exception){
            Timber.wtf("Insert error: %s", e.message)
        }
    }
}

interface IBeerRepository{
    fun getEuropeanBeers(): Flowable<List<BeerViewModel>>
    fun getBeers(): Flowable<List<BeerViewModel>>
    fun getStrongBeers(): Flowable<List<BeerViewModel>>
    fun getGermanBeers(): Flowable<List<BeerViewModel>>
    fun getBelgianBeers(): Flowable<List<BeerViewModel>>
    fun insertBeersIntoDatabase(list: List<BeerDbModel>)
}