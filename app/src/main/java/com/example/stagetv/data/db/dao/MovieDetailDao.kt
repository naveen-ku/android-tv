package com.example.stagetv.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stagetv.data.db.entity.Genre
import com.example.stagetv.data.db.entity.SpokenLanguage
import com.example.stagetv.data.db.entity.movie.MovieDetails

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetails: MovieDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpokenLanguages(spokenLanguage: List<SpokenLanguage>)

    @Query("SELECT * FROM movie_details WHERE id = :mId")
    suspend fun getMovieDetailBy(mId: String): MovieDetails?

    @Query("DELETE FROM movie_details")
    suspend fun deleteAllMovieDetails()

}