package com.example.stagetv.data.db.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_thumbnails")
data class MovieThumbnail(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("poster_path") val posterPath: String,
    val title: String
)