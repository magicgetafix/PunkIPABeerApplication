package com.magicgetafix.android.punkipabeerapplication.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.magicgetafix.android.punkipabeerapplication.services.BeerRepository
import com.magicgetafix.android.punkipabeerapplication.services.IBeerRepository
import com.moneypenny.telephoneanswering.schedulers.ISchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context,
                                        private val beerRepository: IBeerRepository,
                                        private val schedulers: ISchedulers): ViewModel() {


    init {
        beerRepository.getBeers()
            .subscribeOn(schedulers.background)
            .observeOn(schedulers.ui)
            .subscribe()
    }
}