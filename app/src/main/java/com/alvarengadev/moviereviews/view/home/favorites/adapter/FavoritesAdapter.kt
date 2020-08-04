package com.alvarengadev.moviereviews.view.home.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.view.home.common.OnReviewClickListener

class FavoritesAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<FavoritesViewHolder>() {

    private lateinit var onReviewClickListener: OnReviewClickListener

    fun setOnItemClickListener(onReviewClickListener: OnReviewClickListener) {
        this.onReviewClickListener = onReviewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoritesViewHolder(view, onReviewClickListener, reviews)
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

}