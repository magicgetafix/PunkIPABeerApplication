package com.magicgetafix.android.punkipabeerapplication.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel
import com.magicgetafix.android.punkipabeerapplication.model.livedata.SingleLiveEvent
import com.magicgetafix.android.punkipabeerapplication.services.BeerRepository
import com.magicgetafix.android.punkipabeerapplication.services.IBeerRepository
import com.moneypenny.telephoneanswering.schedulers.ISchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context,
                                        private val beerRepository: IBeerRepository,
                                        private val schedulers: ISchedulers): ViewModel() {

    private val allBeersMutableLiveData: SingleLiveEvent<List<BeerViewModel>> = SingleLiveEvent<List<BeerViewModel>>()
    private val europeanBeersMutableLiveData: SingleLiveEvent<List<BeerViewModel>> = SingleLiveEvent<List<BeerViewModel>>()
    private val strongBeersMutableLiveData: SingleLiveEvent<List<BeerViewModel>> = SingleLiveEvent<List<BeerViewModel>>()
    private val belgianBeersMutableLiveData: SingleLiveEvent<List<BeerViewModel>> = SingleLiveEvent<List<BeerViewModel>>()
    private val germanBeersMutableLiveData: SingleLiveEvent<List<BeerViewModel>> = SingleLiveEvent<List<BeerViewModel>>()

    init {
        beerRepository.getBeers()
            .subscribeOn(schedulers.background)
            .observeOn(schedulers.ui)
            .subscribe({
                allBeersMutableLiveData.postValue(it)
            }, {
                Timber.wtf("Failed to get beer list data :" + it.message)
            })

        beerRepository.getEuropeanBeers()
            .subscribeOn(schedulers.background)
            .observeOn(schedulers.ui)
            .subscribe({
                europeanBeersMutableLiveData.postValue(it)
            }, {
                Timber.wtf("Failed to get beer list data :" + it.message)
            })

        beerRepository.getStrongBeers()
            .subscribeOn(schedulers.background)
            .observeOn(schedulers.ui)
            .subscribe({
                strongBeersMutableLiveData.postValue(it)
            }, {
                Timber.wtf("Failed to get beer list data :" + it.message)
            })

        beerRepository.getGermanBeers()
            .subscribeOn(schedulers.background)
            .observeOn(schedulers.ui)
            .subscribe({
                germanBeersMutableLiveData.postValue(it)
            }, {
                Timber.wtf("Failed to get beer list data :" + it.message)
            })

        beerRepository.getBelgianBeers()
            .subscribeOn(schedulers.background)
            .observeOn(schedulers.ui)
            .subscribe({
                belgianBeersMutableLiveData.postValue(it)
            }, {
                Timber.wtf("Failed to get beer list data :" + it.message)
            })
    }

    fun getAllBeersLiveData(): SingleLiveEvent<List<BeerViewModel>>{
        return allBeersMutableLiveData
    }

    fun getEuropeanBeersLiveData(): SingleLiveEvent<List<BeerViewModel>>{
        return europeanBeersMutableLiveData
    }

    fun getStrongBeersLiveData(): SingleLiveEvent<List<BeerViewModel>>{
        return strongBeersMutableLiveData
    }

    fun getBelgianBeersLiveData(): SingleLiveEvent<List<BeerViewModel>>{
        return belgianBeersMutableLiveData
    }

    fun getGermanBeersLiveData(): SingleLiveEvent<List<BeerViewModel>>{
        return germanBeersMutableLiveData
    }


}