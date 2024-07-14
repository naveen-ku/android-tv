package com.example.stagetv.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stagetv.data.db.entity.movie.MoviesList


@Dao
interface MovieListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(moviesList: MoviesList)

    @Query("DELETE FROM movies_list")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movies_list")
    suspend fun getMovieList(): MoviesList?

}