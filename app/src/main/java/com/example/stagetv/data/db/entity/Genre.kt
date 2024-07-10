package com.example.stagetv.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("genre")
data class Genre(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)