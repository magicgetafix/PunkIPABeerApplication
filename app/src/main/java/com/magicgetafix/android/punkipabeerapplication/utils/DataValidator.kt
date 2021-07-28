package com.magicgetafix.android.punkipabeerapplication.utils

import com.magicgetafix.android.punkipabeerapplication.Constants
import com.magicgetafix.android.punkipabeerapplication.api.response.Beer

object DataValidator {

    fun isValidBeer(beer: Beer): Boolean {
        if (beer.description.isNullOrEmpty()|| beer.description!!.length > Constants.DESCRIPTION_LENGTH_LIMIT) return false
        if (beer.name.isNullOrEmpty()) return false
        if (beer.tagline.isNullOrEmpty()) return false
        if (beer.image_url == null) return false
        return true
    }
}