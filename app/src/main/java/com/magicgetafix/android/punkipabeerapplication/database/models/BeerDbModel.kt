package com.magicgetafix.android.punkipabeerapplication.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BeerDbModel (@PrimaryKey val id: Int, val name: String, val imageUrl: String, val strength: Int, val tagline: String, val description: String, val foodPairingNotes: List<String>)
