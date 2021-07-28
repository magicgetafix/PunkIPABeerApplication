package com.magicgetafix.android.punkipabeerapplication.ui.beer_list

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation.INFINITE
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
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
    private var fragmentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentBeerListBinding.inflate(layoutInflater)
        //scope MainViewModel to MainActivity so it functions almost as a global instance
        mainViewModel =
            ViewModelProvider(activity as MainActivity).get(MainViewModel::class.java)
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
        animate(0.0, 360.0, 1300)
        if (mainViewModel.beersAreLoaded){
            fragmentBinding.spinningBottle.visibility = View.GONE
            fragmentBinding.loadingText.visibility = View.GONE
            fragmentBinding.loadingView.visibility = View.GONE
        }

    }

    fun setAllBeerList(beerList: List<BeerViewModel>){
        if (!beerList.isEmpty()) {
            val allBeersStr = context?.getString(R.string.all_beers) ?: ""
            fragmentBinding.allBeersView.setContent(allBeersStr, beerList)
            fragmentBinding.allBeersView.visibility = View.VISIBLE
            fadeOutLoadingView()
        }
    }

    private fun fadeOutLoadingView() {

        gradualFade(fragmentBinding.spinningBottle)
        gradualFade(fragmentBinding.loadingView)
        gradualFade(fragmentBinding.loadingText)
    }

    private fun gradualFade(view: View) {
        view.animate()
            .alpha(0f)
            .setDuration(3000)
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                    //do nothing
                }

                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {
                    view.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    //do nothing
                }

            })
            .start()
    }

    fun setEuropeanBeerList(beerList: List<BeerViewModel>){
        if (!beerList.isEmpty()) {
            val allBeersStr = context?.getString(R.string.european_beers) ?: ""
            fragmentBinding.europeanBeersView.setContent(allBeersStr, beerList)
            fragmentBinding.europeanBeersView.visibility = View.VISIBLE
        }
    }

    fun setStrongBeerList(beerList: List<BeerViewModel>){
        if (!beerList.isEmpty()) {
            val allBeersStr = context?.getString(R.string.strong_beers) ?: ""
            fragmentBinding.strongBeersView.setContent(allBeersStr, beerList)
            fragmentBinding.strongBeersView.visibility = View.VISIBLE
        }
    }

    fun setGermanBeerList(beerList: List<BeerViewModel>){
        if (!beerList.isEmpty()) {
            val allBeersStr = context?.getString(R.string.german_beers) ?: ""
            fragmentBinding.germanBeersView.setContent(allBeersStr, beerList)
            fragmentBinding.germanBeersView.visibility = View.VISIBLE
        }
    }

    fun setBelgianBeerList(beerList: List<BeerViewModel>){
        if (!beerList.isEmpty()) {
            val allBeersStr = context?.getString(R.string.belgian_beers) ?: ""
            fragmentBinding.belgianBeersView.setContent(allBeersStr, beerList)
            fragmentBinding.belgianBeersView.visibility = View.VISIBLE
        }
    }

    private fun animate(fromDegrees: Double, toDegrees: Double, durationMillis: Long) {
        val rotate = RotateAnimation(
            fromDegrees.toFloat(), toDegrees.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.65f
        )
        rotate.duration = durationMillis
        rotate.repeatCount = INFINITE
        rotate.isFillEnabled = true
        rotate.fillAfter = true
        fragmentBinding.spinningBottle.startAnimation(rotate)
    }

}