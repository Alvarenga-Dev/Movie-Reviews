package com.alvarengadev.moviereviews.data.api.network.reviews.response

import com.squareup.moshi.Json

data class ReviewBodyResponse (
    @field:Json(name = "display_title") val title: String,
    @field:Json(name = "byline") val author: String,
    @field:Json(name = "headline") val headline: String,
    @field:Json(name = "summary_short") val summary: String,
    @field:Json(name = "publication_date") val publicationDate: String,
    @field:Json(name = "link") val linkBody: LinkBodyResponse
)