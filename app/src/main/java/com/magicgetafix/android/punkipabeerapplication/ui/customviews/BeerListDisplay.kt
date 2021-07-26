package com.magicgetafix.android.punkipabeerapplication.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.magicgetafix.android.punkipabeerapplication.databinding.BeerListDisplayBinding
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel

class BeerListDisplay: ConstraintLayout {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private lateinit var ui: BeerListDisplayBinding

    init{
        BeerListDisplayBinding.inflate(LayoutInflater.from(context), this, true).also {
            ui = it
        }
    }

    fun setContent(title: String, listBeer: List<BeerViewModel>){
        ui.titleView.text = title
    }
}

