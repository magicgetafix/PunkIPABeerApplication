package com.magicgetafix.android.punkipabeerapplication.ui.beer_details_fragment

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.magicgetafix.android.punkipabeerapplication.R
import com.magicgetafix.android.punkipabeerapplication.databinding.BeerDetailsFragmentBinding
import com.magicgetafix.android.punkipabeerapplication.databinding.FragmentBeerListBinding
import com.magicgetafix.android.punkipabeerapplication.main.MainActivity
import com.magicgetafix.android.punkipabeerapplication.main.MainViewModel

class BeerDetailsFragment : DialogFragment() {

    private lateinit var fragmentBinding: BeerDetailsFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBinding = BeerDetailsFragmentBinding.inflate(layoutInflater)
        //scope MainViewModel to MainActivity so it functions almost as a global instance
        viewModel = ViewModelProvider(activity as MainActivity).get(MainViewModel::class.java)
        return fragmentBinding.root
    }


}