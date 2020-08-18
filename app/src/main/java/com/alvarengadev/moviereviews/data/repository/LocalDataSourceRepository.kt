package com.alvarengadev.moviereviews.data.repository

import com.alvarengadev.moviereviews.data.database.dao.ReviewsFavoritesDao
import com.alvarengadev.moviereviews.data.domain.Review

class LocalDataSourceRepository(
    private val dao: ReviewsFavoritesDao
) {

    suspend fun addFavorite(review: Review) = dao.insert(review)

    suspend fun removeFavorite(review: Review) = dao.delete(review)

    suspend fun getReviews(): ArrayList<Review> = ArrayList(dao.getAll())

}