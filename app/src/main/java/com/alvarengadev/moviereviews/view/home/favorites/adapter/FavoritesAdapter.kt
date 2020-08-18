package com.alvarengadev.moviereviews.view.home.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.utils.toLowerCase
import com.alvarengadev.moviereviews.view.home.common.OnReviewClickListener
import kotlin.collections.ArrayList

class FavoritesAdapter(
    favorites: ArrayList<Review>
) : RecyclerView.Adapter<FavoritesViewHolder>(), Filterable {

    private val listFavoritesFull = ArrayList(favorites)
    private val listFavorites = favorites
    private lateinit var onReviewClickListener: OnReviewClickListener

    fun setOnItemClickListener(
        onReviewClickListener: OnReviewClickListener
    ) {
        this.onReviewClickListener = onReviewClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoritesViewHolder(view, onReviewClickListener, listFavorites)
    }

    override fun getItemCount(): Int = listFavorites.size

    override fun onBindViewHolder(
        holder: FavoritesViewHolder,
        position: Int
    ) {
        holder.bind(listFavorites[position])
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(
            constraint: CharSequence
        ): FilterResults {
            val filteredList = ArrayList<Review>()
            if (constraint.isEmpty()) {
                filteredList.addAll(listFavoritesFull)
            } else {
                val filterPattern =
                    toLowerCase(constraint.toString()).trim { predicate -> predicate <= ' ' }
                for (item in listFavoritesFull) {
                    if (toLowerCase(item.title).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(
            constraint: CharSequence,
            results: FilterResults
        ) {
            listFavorites.clear()
            listFavorites.addAll(results.values as ArrayList<Review>)
            notifyDataSetChanged()
        }

    }

}