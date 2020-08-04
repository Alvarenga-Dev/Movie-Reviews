package com.alvarengadev.moviereviews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alvarengadev.moviereviews.data.database.dao.ReviewsFavoritesDao
import com.alvarengadev.moviereviews.data.domain.Review

@Database(entities = [Review::class], version = 1, exportSchema = false)
abstract class ReviewsFavoritesDatabase : RoomDatabase() {
    abstract val reviewsFavoritesDao: ReviewsFavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: ReviewsFavoritesDatabase? = null

        fun getDatabase(context: Context): ReviewsFavoritesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReviewsFavoritesDatabase::class.java,
                    "my_reviews_favorites_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}