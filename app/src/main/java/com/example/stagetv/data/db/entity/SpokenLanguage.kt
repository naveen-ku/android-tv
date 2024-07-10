package com.example.stagetv.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("languages")
data class SpokenLanguage(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso6391: String
)
