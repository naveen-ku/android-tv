package com.example.stagetv.data.db.entity.tvseries

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity("tv_series_thumbnail")
data class TvSeriesThumbnail(
    val id: Int,
    @SerializedName("media_type") val mediaType: String,
    val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("poster_path") val posterPath: String
)
