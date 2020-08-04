package com.alvarengadev.moviereviews.data.api.network

import com.alvarengadev.moviereviews.data.api.network.reviews.ReviewsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/movies/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun reviewService(): ReviewsService = retrofit.create(ReviewsService::class.java)
}