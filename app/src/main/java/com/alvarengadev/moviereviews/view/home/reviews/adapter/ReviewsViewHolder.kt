package com.alvarengadev.moviereviews.view.home.reviews.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.view.home.common.OnReviewClickListener

class ReviewsViewHolder(
    itemView: View,
    onReviewClickListener: OnReviewClickListener,
    reviews: ArrayList<Review>
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
           val positionRacy = adapterPosition
            if (positionRacy != RecyclerView.NO_POSITION) {
                onReviewClickListener.onItemClick(reviews[positionRacy])
            }
        }
    }

    private val tvTitleReview = itemView.findViewById(R.id.tv_title_favorite) as TextView
    private val tvHeadlineReview = itemView.findViewById(R.id.tv_headline_favorite) as TextView
    private val tvPublicationDate = itemView.findViewById(R.id.tv_publication_date_review) as TextView

    fun bind(review: Review) {
        tvTitleReview.text = review.title
        tvHeadlineReview.text = review.headline
        tvPublicationDate.text = review.publicationDate
    }
}