package com.magicgetafix.android.punkipabeerapplication.ui.beer_details_fragment

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set up toolbar
        NavigationUI.setupWithNavController(fragmentBinding.toolbar, findNavController())
        val beerId = BeerDetailsFragmentArgs.fromBundle(requireArguments()).beerId
        val beer = viewModel.getBeerFromList(beerId)
        //if beer can't be found
        if (beer == null){
            val beerNotFound = context?.getString(R.string.beer_not_found)
            Toast.makeText(context, beerNotFound, Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        //load image
        Glide.with(this)
            .load(beer?.imageUrl)
            .centerInside()
            .into(fragmentBinding.largeBeerImageView)
            .onLoadFailed(context?.getDrawable(R.drawable.beer_placeholder_icon))
        //set text
        fragmentBinding.beerDescription.text = beer?.description
        fragmentBinding.beerName.text = beer?.name
        fragmentBinding.beerVolumeText.text = (beer?.strength.toString() + "%")
        //concatenate list into bullet point paragraph
        var foodAccompanimentBulletPoints = ""
        beer?.foodPairingNotes?.forEach {
            foodAccompanimentBulletPoints += "\u2022"+" "+it+"\n\n"
        }
        fragmentBinding.foodAccompaniments.text = foodAccompanimentBulletPoints

    }


}