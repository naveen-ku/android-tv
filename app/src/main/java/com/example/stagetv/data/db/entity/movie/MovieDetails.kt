package com.example.stagetv.data.db.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stagetv.data.db.entity.Genre
import com.example.stagetv.data.db.entity.SpokenLanguage
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_details")
data class MovieDetails(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String,
    val genres: List<Genre>,
    val homepage: String,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    val tagline: String,
    val title: String,
    val video: Boolean,
)