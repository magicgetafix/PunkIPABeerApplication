package com.magicgetafix.android.punkipabeerapplication.services

import com.magicgetafix.android.punkipabeerapplication.Constants
import com.magicgetafix.android.punkipabeerapplication.api.BeerApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory: IApiFactory {

    private var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private var okHttpClient: OkHttpClient

    init {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    override fun getBeerApi(): BeerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.PUNK_IPA_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }
}

interface IApiFactory {

    fun getBeerApi(): BeerApi
}