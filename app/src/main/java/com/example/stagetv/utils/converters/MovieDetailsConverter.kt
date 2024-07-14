package com.example.stagetv.utils.converters

import androidx.room.TypeConverter
import com.example.stagetv.data.db.entity.Genre
import com.example.stagetv.data.db.entity.SpokenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieDetailsConverter {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genresString: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(genresString, listType)
    }

    @TypeConverter
    fun fromSpokenLanguageList(spokenLanguages: List<SpokenLanguage>): String {
        return Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguageList(spokenLanguagesString: String): List<SpokenLanguage> {
        val listType = object : TypeToken<List<SpokenLanguage>>() {}.type
        return Gson().fromJson(spokenLanguagesString, listType)
    }
}