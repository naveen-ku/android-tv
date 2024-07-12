package com.example.stagetv.data.db.entity.tvseries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tv_series")
data class TvSeriesList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val page: Int,
    @SerializedName("results") val tvSeriesThumbnails: List<ItemThumbnail>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)