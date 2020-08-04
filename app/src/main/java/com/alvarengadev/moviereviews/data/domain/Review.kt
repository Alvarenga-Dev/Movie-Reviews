package com.alvarengadev.moviereviews.data.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "reviews")
data class Review constructor(
    @field:ColumnInfo(name = "title") var title: String,
    @field:ColumnInfo(name = "author") var author: String,
    @field:ColumnInfo(name = "headline") var headline: String,
    @field:ColumnInfo(name = "summary") var summary: String,
    @field:ColumnInfo(name = "publicationDate") var publicationDate: String,
    @field:ColumnInfo(name = "url") var url: String,
    @field:ColumnInfo(name = "linkDescription") var linkDescription: String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}