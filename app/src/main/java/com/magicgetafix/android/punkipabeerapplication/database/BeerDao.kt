package com.example.punkbeerapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import com.magicgetafix.android.punkipabeerapplication.database.models.BeerDbModel
import io.reactivex.Flowable

@Dao
interface BeerDao {

    @Query("SELECT * FROM beerdbmodel")
    fun getAllBeers(): Flowable<List<BeerDbModel>>

    @Query("SELECT * FROM beerdbmodel WHERE description LIKE ('%'|| 'Belgian' ||'%') OR description LIKE ('%'|| 'Belgium' ||'%')")
    fun getBelgianBeers(): Flowable<List<BeerDbModel>>

    @Query("SELECT * FROM beerdbmodel WHERE description LIKE ('%'|| 'German' ||'%')")
    fun getGermanBeers(): Flowable<List<BeerDbModel>>

    @Query("SELECT * FROM beerdbmodel WHERE description LIKE ('%'|| 'German' ||'%') OR description LIKE ('%'|| 'Belgian' ||'%') OR description LIKE ('%'|| 'Europe' ||'%') OR description LIKE ('%'|| 'French' ||'%') OR description LIKE ('%'|| 'Belgium' ||'%') OR description LIKE ('%'|| 'France' ||'%')")
    fun getAllEuropeanBeers(): Flowable<List<BeerDbModel>>

    @Query("SELECT * FROM beerdbmodel WHERE strength >= 5.0 ORDER BY strength DESC" )
    fun getStrongBeers(): Flowable<List<BeerDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeers(listOfBeers: List<BeerDbModel>)

}