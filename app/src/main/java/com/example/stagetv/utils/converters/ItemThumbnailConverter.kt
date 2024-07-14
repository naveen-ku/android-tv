package com.example.stagetv.utils.converters

import androidx.room.TypeConverter
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemThumbnailConverter {

    @TypeConverter
    fun fromList(genres: List<ItemThumbnail>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toList(genresString: String): List<ItemThumbnail> {
        val listType = object : TypeToken<List<ItemThumbnail>>() {}.type
        return Gson().fromJson(genresString, listType)
    }

}