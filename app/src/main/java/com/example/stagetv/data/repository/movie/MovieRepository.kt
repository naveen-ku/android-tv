package com.example.stagetv.data.repository.movie

import android.util.Log
import com.example.stagetv.data.db.entity.movie.Movies
import com.example.stagetv.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {

    suspend fun getTrendingMovies(): Movies {
        val result = movieService.getTrendingMovies()
        Log.d("Ninja MovieRepository", "result: $result")
        return result
    }
}