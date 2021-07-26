package com.magicgetafix.android.punkipabeerapplication.ui.beer_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.magicgetafix.android.punkipabeerapplication.R
import com.magicgetafix.android.punkipabeerapplication.databinding.FragmentBeerListBinding
import com.magicgetafix.android.punkipabeerapplication.main.MainActivity
import com.magicgetafix.android.punkipabeerapplication.main.MainViewModel
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel


class BeerListFragment : Fragment() {

    lateinit var fragmentBinding: FragmentBeerListBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentBeerListBinding.inflate(layoutInflater)
        //scope MainViewModel to MainActivity so it functions almost as a global instance
        mainViewModel = ViewModelProvider(activity as MainActivity).get(MainViewModel::class.java)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set up toolbar with back behaviour
        NavigationUI.setupWithNavController(fragmentBinding.toolbar, findNavController())
        //observe beer data from viewmodel
        mainViewModel.getAllBeersLiveData().observeForever(Observer { setAllBeerList(it) })
        mainViewModel.getEuropeanBeersLiveData().observeForever(Observer { setEuropeanBeerList(it) })
        mainViewModel.getStrongBeersLiveData().observeForever(Observer { setStrongBeerList(it) })
        mainViewModel.getBelgianBeersLiveData().observeForever(Observer { setBelgianBeerList(it) })
        mainViewModel.getGermanBeersLiveData().observeForever(Observer { setGermanBeerList(it) })

    }

    fun setAllBeerList(beerList: List<BeerViewModel>){
        val allBeersStr = context?.getString(R.string.all_beers) ?: ""
        fragmentBinding.allBeersView.setContent(allBeersStr, beerList)
    }

    fun setEuropeanBeerList(beerList: List<BeerViewModel>){
        val allBeersStr = context?.getString(R.string.european_beers) ?: ""
        fragmentBinding.europeanBeersView.setContent(allBeersStr, beerList)
    }

    fun setStrongBeerList(beerList: List<BeerViewModel>){
        val allBeersStr = context?.getString(R.string.strong_beers) ?: ""
        fragmentBinding.strongBeersView.setContent(allBeersStr, beerList)
    }

    fun setGermanBeerList(beerList: List<BeerViewModel>){
        val allBeersStr = context?.getString(R.string.german_beers) ?: ""
        fragmentBinding.germanBeersView.setContent(allBeersStr, beerList)
    }

    fun setBelgianBeerList(beerList: List<BeerViewModel>){
        val allBeersStr = context?.getString(R.string.belgian_beers) ?: ""
        fragmentBinding.belgianBeersView.setContent(allBeersStr, beerList)
    }

}