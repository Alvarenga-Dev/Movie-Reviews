package com.alvarengadev.moviereviews.data.api.mapper

import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.data.api.network.reviews.response.ReviewBodyResponse

class ReviewMapper {
    companion object {
        fun responseToDomain(listReviewBodyResponse: List<ReviewBodyResponse>): ArrayList<Review> {
            val listReview = ArrayList<Review>()

            for (reviewResponse in listReviewBodyResponse) {
                val review = Review(
                    reviewResponse.title,
                    reviewResponse.author,
                    reviewResponse.headline,
                    reviewResponse.summary,
                    reviewResponse.publicationDate,
                    reviewResponse.linkBody.url,
                    reviewResponse.linkBody.linkDescription
                )

                listReview.add(review)
            }

            return listReview
        }
    }
}