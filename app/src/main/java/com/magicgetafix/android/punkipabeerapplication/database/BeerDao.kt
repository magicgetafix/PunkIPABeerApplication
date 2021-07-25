package com.example.punkbeerapplication.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import com.magicgetafix.android.punkipabeerapplication.database.models.BeerDbModel
import io.reactivex.Flowable

interface BeerDao {

    @Query("SELECT * FROM beerdbmodel")
    fun getAllBeers(): Flowable<List<BeerDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeers(listOfBeers: List<BeerDbModel>)
}