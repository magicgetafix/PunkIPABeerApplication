package com.magicgetafix.android.punkipabeerapplication.api

import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface BeerApi {
    @GET("/beers")
    fun getAllBeers(): Observable<ArrayList<Beer>>
}