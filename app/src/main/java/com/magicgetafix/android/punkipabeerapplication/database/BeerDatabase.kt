package com.magicgetafix.android.punkipabeerapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.punkbeerapplication.database.BeerDao
import com.magicgetafix.android.punkipabeerapplication.database.models.BeerDbModel

@Database(entities = arrayOf(BeerDbModel::class), version = 1)
abstract class BeerDatabase: RoomDatabase()  {

    abstract fun beerDao(): BeerDao

}