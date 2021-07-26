package com.magicgetafix.android.punkipabeerapplication.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.magicgetafix.android.punkipabeerapplication.databinding.BeerListDisplayBinding
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel
import com.magicgetafix.android.punkipabeerapplication.ui.beer_list.BeerListAdapter

class BeerListDisplay: ConstraintLayout {

    private var beerListAdapter: BeerListAdapter? = null

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
        beerListAdapter = BeerListAdapter(listBeer)
        ui.beerRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        ui.beerRecyclerView.adapter = beerListAdapter
    }
}

