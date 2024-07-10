package com.example.stagetv.data.db.entity.tvseries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stagetv.data.db.entity.Genre
import com.example.stagetv.data.db.entity.SpokenLanguage
import com.google.gson.annotations.SerializedName

@Entity("tv_series_details")
data class TvSeriesDetails(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val genres: List<Genre>,
    val homepage: String,
    val languages: List<String>,
    val name: String,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_name") val originalName: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val type: String
)