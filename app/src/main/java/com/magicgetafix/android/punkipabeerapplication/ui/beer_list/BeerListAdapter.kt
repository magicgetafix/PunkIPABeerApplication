package com.magicgetafix.android.punkipabeerapplication.ui.beer_list

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.magicgetafix.android.punkipabeerapplication.R
import com.magicgetafix.android.punkipabeerapplication.databinding.BeerItemBinding
import com.magicgetafix.android.punkipabeerapplication.model.BeerViewModel
import com.magicgetafix.android.punkipabeerapplication.ui.beer_details_fragment.BeerDetailsFragment

class BeerListAdapter constructor(private val beerList: List<BeerViewModel>): RecyclerView.Adapter<BeerListViewHolder>() {

    lateinit var context: Context
    var drawableHashMap: HashMap<Int, Drawable> = hashMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        context = parent.context
        val binding = BeerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val beer = beerList.get(position)
        if (drawableHashMap.get(position) != null){
            holder.binding.beerImageView.setImageDrawable(drawableHashMap.get(position))
        }
        else {
            Glide.with(holder.itemView)
                .load(beer.imageUrl)
                .centerInside()
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.binding.beerImageView.setImageDrawable(context.getDrawable(R.drawable.beer_placeholder_icon))
                        holder.binding.beerPlaceholderImageView.visibility = View.VISIBLE
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        drawableHashMap.put(position, resource!!)
                        holder.binding.beerImageView.setImageDrawable(resource)
                        holder.binding.beerPlaceholderImageView.visibility = View.INVISIBLE
                        return true
                    }

                })
                .into(holder.binding.beerImageView)
        }

        holder.binding.beerTaglineView.text = beer.tagline
        holder.binding.beerTitleView.text = beer.name
        holder.binding.beerVolumeView.text = context.getString(R.string.abv_string) + beer.strength + "%"
        if (beer.strength >= 5.0){
            holder.binding.alcoholStrengthIcon.visibility = View.VISIBLE
        }
        else{
            holder.binding.alcoholStrengthIcon.visibility = View.INVISIBLE
        }
        holder.itemView.setOnClickListener {
            val action = BeerListFragmentDirections.toBeerDetailsFragment(position)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return beerList.size
    }


}

class BeerListViewHolder(val binding: BeerItemBinding) : RecyclerView.ViewHolder(binding.root) {

}