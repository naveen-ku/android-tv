package com.example.stagetv.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stagetv.data.db.dao.MovieDetailDao
import com.example.stagetv.data.db.dao.MovieListDao
import com.example.stagetv.data.db.dao.TvSeriesDetailDao
import com.example.stagetv.data.db.dao.TvSeriesListDao
import com.example.stagetv.data.db.dao.UserDao
import com.example.stagetv.data.db.entity.Genre
import com.example.stagetv.data.db.entity.SpokenLanguage
import com.example.stagetv.data.db.entity.User
import com.example.stagetv.data.db.entity.movie.MovieDetails
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import com.example.stagetv.utils.converters.ItemThumbnailConverter
import com.example.stagetv.utils.converters.MovieDetailsConverter

@Database(
    entities = [
        User::class,
        MovieDetails::class,
        MoviesList::class,
        Genre::class,
        SpokenLanguage::class,
        TvSeriesList::class,
        TvSeriesDetails::class
    ],
    version = 1
)
@TypeConverters(
    MovieDetailsConverter::class,
    ItemThumbnailConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun movieDetailDao(): MovieDetailDao

    abstract fun movieListDao(): MovieListDao

    abstract fun tvSeriesDetailDao(): TvSeriesDetailDao

    abstract fun tvSeriesListDao(): TvSeriesListDao

}