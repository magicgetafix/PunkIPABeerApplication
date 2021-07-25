package com.magicgetafix.android.punkipabeerapplication.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.magicgetafix.android.punkipabeerapplication.database.typeconverter.StringConverter

@Entity
class BeerDbModel(@PrimaryKey val id: Int, val name: String, val imageUrl: String, val strength: Double, val tagline: String, val description: String, @TypeConverters(StringConverter::class) val foodPairingNotesList: List<String>)
