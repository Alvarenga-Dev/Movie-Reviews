package com.alvarengadev.moviereviews.view.home.reviews.adapter

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

class ReviewsAdapter(
    reviews: ArrayList<Review>
) : RecyclerView.Adapter<ReviewsViewHolder>(), Filterable {

    private val listReviewsFull = ArrayList(reviews)
    private val listReviews = reviews
    private lateinit var onReviewClickListener: OnReviewClickListener

    fun setOnItemClickListener(
        onReviewClickListener: OnReviewClickListener
    ) {
        this.onReviewClickListener = onReviewClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_critic_review, parent, false)
        return ReviewsViewHolder(view, onReviewClickListener, listReviews)
    }

    override fun getItemCount(): Int = listReviews.size

    override fun onBindViewHolder(
        holder: ReviewsViewHolder,
        position: Int
    ) {
        holder.bind(listReviews[position])
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(
            constraint: CharSequence
        ): FilterResults {
            val filteredList = ArrayList<Review>()
            if (constraint.isEmpty()) {
                filteredList.addAll(listReviewsFull)
            } else {
                val filterPattern =
                    toLowerCase(constraint.toString()).trim { predicate -> predicate <= ' ' }
                for (item in listReviewsFull) {
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
            listReviews.clear()
            listReviews.addAll(results.values as ArrayList<Review>)
            notifyDataSetChanged()
        }
    }
}