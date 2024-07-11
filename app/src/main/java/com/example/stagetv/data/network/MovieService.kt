package com.example.stagetv.data.network

import com.example.stagetv.data.db.entity.movie.MovieDetails
import com.example.stagetv.data.db.entity.movie.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("trending/movie/day?language=en-US/")
    suspend fun getTrendingMovies(): MoviesList

    @GET("movie/popular")
    suspend fun getPopularMoviesList(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesList

    @GET("movie/")
    suspend fun getMovieDetail(@Query("movie_id") movieId: Int): MovieDetails
}