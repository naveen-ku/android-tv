package com.example.stagetv.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stagetv.data.db.dao.MovieDetailDao
import com.example.stagetv.data.db.dao.UserDao
import com.example.stagetv.data.db.entity.Genre
import com.example.stagetv.data.db.entity.SpokenLanguage
import com.example.stagetv.data.db.entity.User
import com.example.stagetv.data.db.entity.movie.MovieDetails
import com.example.stagetv.utils.MovieDetailsConverter

@Database(
    entities = [User::class, MovieDetails::class, Genre::class, SpokenLanguage::class],
    version = 1
)
@TypeConverters(MovieDetailsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun movieDetailDao(): MovieDetailDao

}