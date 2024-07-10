package com.example.stagetv.data.network

import com.example.stagetv.data.db.entity.tvseries.TvSeries
import retrofit2.http.GET

interface TvSeriesService {

    @GET("trending/tv/day?language=en-US/")
    suspend fun getTrendingMovies(): TvSeries
}