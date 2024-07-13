package com.example.stagetv.data.repository.tvseries

import android.util.Log
import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import com.example.stagetv.data.network.TvSeriesService
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(
    private val tvSeriesService: TvSeriesService,
    private val appDatabase: AppDatabase
) {

    suspend fun getTvSeriesDetails(id: Int): TvSeriesDetails {
        val result = tvSeriesService.getTvSeriesDetail(id)
        Log.d("Ninja MovieRepository", "getTvSeriesDetails $result")
        return result
    }

    suspend fun getTrendingTvSeries(): TvSeriesList {
        val result = tvSeriesService.getTrendingTvSeries()
        Log.d("Ninja MovieRepository", "result: $result")
        return result;
    }

}