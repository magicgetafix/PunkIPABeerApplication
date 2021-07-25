package com.magicgetafix.android.punkipabeerapplication.di

import android.content.Context
import com.magicgetafix.android.punkipabeerapplication.services.*
import com.moneypenny.telephoneanswering.schedulers.ISchedulers
import com.moneypenny.telephoneanswering.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    internal fun provideDatabaseProvider(@ApplicationContext context: Context): IDatabaseProvider {
        return DatabaseProvider(context)
    }

    @Singleton
    @Provides
    internal fun provideApiFactory(): IApiFactory {
        return ApiFactory()
    }

    @Singleton
    @Provides
    internal fun provideSchedulers(): ISchedulers {
        return RxSchedulers()
    }


    @Singleton
    @Provides
    internal fun provideBeerRepository(databaseProvider: IDatabaseProvider, apiFactory: IApiFactory, schedulers: ISchedulers): IBeerRepository {
        return BeerRepository(databaseProvider, apiFactory, schedulers)
    }

}