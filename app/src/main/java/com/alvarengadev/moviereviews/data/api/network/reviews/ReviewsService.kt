package com.alvarengadev.moviereviews.data.api.network.reviews

import com.alvarengadev.moviereviews.data.api.network.reviews.response.ReviewsResult
import com.alvarengadev.moviereviews.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewsService {
    @GET("reviews/search.json")
    fun list(@Query("api-key") apiKey: String = API_KEY) : Call<ReviewsResult>
}