package com.alvarengadev.moviereviews.view.home.favorites.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.view.home.common.OnReviewClickListener

class FavoritesViewHolder(
    itemView: View,
    onReviewClickListener: OnReviewClickListener,
    reviews: List<Review>
) : RecyclerView.ViewHolder(itemView){

    init {
        itemView.setOnClickListener {
            val positionRcy = adapterPosition
            if (positionRcy != RecyclerView.NO_POSITION) {
                onReviewClickListener.onItemClick(reviews[positionRcy])
            }
        }
    }

    private val tvTitleFavorite = itemView.findViewById(R.id.tv_title_favorite) as TextView
    private val tvHeadlineFavorite = itemView.findViewById(R.id.tv_headline_favorite) as TextView

    fun bind(review: Review) {
        tvTitleFavorite.text = review.title
        tvHeadlineFavorite.text = review.headline
    }

}