package com.magicgetafix.android.punkipabeer.application.utils

import com.magicgetafix.android.punkipabeerapplication.Constants
import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import com.magicgetafix.android.punkipabeerapplication.database.models.BeerDbModel
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel

object Mapper {

    fun toDbModel(response: Beer): BeerDbModel {
        val volume = response.abv.toDouble()
        val name = response.name ?: ""
        val imageUrl = response.image_url ?: ""
        val id = response.id
        val tagline = response.tagline ?: ""
        val description = response.description ?: ""
        val foodPairingNotes = response.food_pairing
        return BeerDbModel(id, name, imageUrl, volume, tagline, description, foodPairingNotes)
    }

    fun toViewModel(dbModel: BeerDbModel): BeerViewModel?{
        if (dbModel.name.isEmpty()) return null
        if (dbModel.tagline.isEmpty()) return null
        if (dbModel.description.length > Constants.DESCRIPTION_LENGTH_LIMIT) return  null
        return BeerViewModel(dbModel.id, dbModel.name, dbModel.imageUrl, dbModel.strength, dbModel.tagline, dbModel.description, dbModel.foodPairingNotesList)
    }


}