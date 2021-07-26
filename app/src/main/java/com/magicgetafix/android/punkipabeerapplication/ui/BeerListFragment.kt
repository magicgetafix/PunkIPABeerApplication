package com.magicgetafix.android.punkipabeerapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magicgetafix.android.punkipabeerapplication.R
import com.magicgetafix.android.punkipabeerapplication.databinding.FragmentBeerListBinding


class BeerListFragment : Fragment() {

    lateinit var fragmentBinding: FragmentBeerListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentBeerListBinding.inflate(layoutInflater)
        return fragmentBinding.root
    }

}