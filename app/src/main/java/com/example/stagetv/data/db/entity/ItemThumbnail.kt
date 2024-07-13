package com.example.stagetv.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Combined the movie item thumbnail and tv series thumbnail item
// movie item -> title
// tv series item -> name
@Entity(tableName = "item_thumbnails")
data class ItemThumbnail(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath:String,
    @SerializedName("media_type") val mediaType: String,
    val title:String?,
    val name:String?,
    val overview:String
)