package com.alvarengadev.moviereviews.data.database.dao

import androidx.room.*
import com.alvarengadev.moviereviews.data.domain.Review

@Dao
interface ReviewsFavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: Review)

    @Delete
    suspend fun delete(review: Review)

    @Query("SELECT * FROM reviews")
    suspend fun getAll(): List<Review>

}