package com.alvarengadev.moviereviews.data.api.network.reviews.response

import com.squareup.moshi.Json

data class ReviewsResult (
    @field:Json(name = "results") val results: List<ReviewBodyResponse>
)