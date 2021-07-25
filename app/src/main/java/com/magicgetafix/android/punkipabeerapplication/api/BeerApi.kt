package com.magicgetafix.android.punkipabeerapplication.api

import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface BeerApi {
    @GET("/beers")
    fun getAllBeers(): Observable<ArrayList<Beer>>
}