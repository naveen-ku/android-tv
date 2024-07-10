package com.example.stagetv.data.network

import com.example.stagetv.data.db.entity.movie.Movies
import retrofit2.http.GET

interface MovieService {
    @GET("trending/movie/day?language=en-US/")
    suspend fun getTrendingMovies(): Movies
}