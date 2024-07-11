package com.example.stagetv.data.db.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_list")
data class MoviesList(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val page: Int,
    @SerializedName("results") val movieThumbnails: List<MovieThumbnail>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)