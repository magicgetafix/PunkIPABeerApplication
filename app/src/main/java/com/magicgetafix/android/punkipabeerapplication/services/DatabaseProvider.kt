package com.magicgetafix.android.punkipabeerapplication.services

import android.content.Context
import androidx.room.Room
import com.magicgetafix.android.punkipabeerapplication.Constants
import com.magicgetafix.android.punkipabeerapplication.database.BeerDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

open class DatabaseProvider @Inject constructor(@ApplicationContext val context: Context): IDatabaseProvider {

    override fun getDatabase(): BeerDatabase {
        val db = Room.databaseBuilder(context, BeerDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
        return db
    }

}

interface IDatabaseProvider{

    fun getDatabase(): BeerDatabase
}