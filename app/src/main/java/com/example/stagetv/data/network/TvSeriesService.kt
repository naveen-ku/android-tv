package com.example.stagetv.data.network

import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import retrofit2.http.GET
import retrofit2.http.Query

interface TvSeriesService {

    @GET("trending/tv/day?language=en-US/")
    suspend fun getTrendingMovies(): TvSeriesList

    @GET("tv/popular")
    suspend fun getTvSeriesList(
        @Query("language") language: String, @Query("page") page: Int
    ): TvSeriesList

    @GET("tv")
    suspend fun getTvSeriesDetail(@Query("series_id") seriesId: Int): TvSeriesDetails
}