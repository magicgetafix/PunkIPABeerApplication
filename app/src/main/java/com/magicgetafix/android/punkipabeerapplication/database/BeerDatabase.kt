package com.magicgetafix.android.punkipabeerapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.punkbeerapplication.database.BeerDao
import com.magicgetafix.android.punkipabeerapplication.database.models.BeerDbModel
import com.magicgetafix.android.punkipabeerapplication.database.typeconverter.StringConverter

@Database(entities = [BeerDbModel::class], version = 1, exportSchema = false)
@TypeConverters(StringConverter::class)
abstract class BeerDatabase: RoomDatabase()  {

    abstract fun beerDao(): BeerDao

}