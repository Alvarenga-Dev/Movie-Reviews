package com.alvarengadev.moviereviews.view.home.reviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.view.home.common.OnReviewClickListener

class ReviewsAdapter(
    private val reviews: ArrayList<Review>
) : RecyclerView.Adapter<ReviewsViewHolder>() {

    private lateinit var onReviewClickListener: OnReviewClickListener

    fun setOnItemClickListener(onReviewClickListener: OnReviewClickListener) {
        this.onReviewClickListener = onReviewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_critic_review, parent, false)
        return ReviewsViewHolder(view, onReviewClickListener, reviews)
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(reviews[position])
    }
}