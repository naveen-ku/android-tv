package com.example.stagetv.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item_thumbnails")
data class ItemThumbnail(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("poster_path") val posterPath: String,
)