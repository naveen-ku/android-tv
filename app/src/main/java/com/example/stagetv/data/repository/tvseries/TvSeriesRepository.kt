package com.example.stagetv.data.repository.tvseries

import android.content.Context
import android.util.Log
import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import com.example.stagetv.data.network.TvSeriesService
import com.example.stagetv.utils.NetworkUtils
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(
    private val tvSeriesService: TvSeriesService,
    private val appDatabase: AppDatabase,
    private val appContext: Context
) {

    suspend fun getTvSeriesDetails(id: Int): TvSeriesDetails? {
        return if (NetworkUtils.isInternetAvailable(appContext)) {
            Log.d("Ninja TvSeriesRepository", "online:: getTvSeriesDetails() check DB")
            var result = getTvSeriesDetailsFromDb(id)
            if (result == null) {
                Log.d("Ninja TvSeriesRepository", "getTvSeriesDetails() from network")
                result = tvSeriesService.getTvSeriesDetail(id)
                insertTvSeriesDetailsToDb(result)
            }
            result
        } else {
            Log.d("Ninja TvSeriesRepository", "offline:: getTvSeriesDetails() from db")
            val result = getTvSeriesDetailsFromDb(id)
            result
        }
    }

    suspend fun getTrendingTvSeries(): TvSeriesList? {
        return if (NetworkUtils.isInternetAvailable(appContext)) {
            Log.d("Ninja TvSeriesRepository", "getTrendingTvSeries() from network")
            val result = tvSeriesService.getTrendingTvSeries()
            insertTvSeriesListToDb(result)
            result
        } else {
            Log.d("Ninja TvSeriesRepository", "offline:: getTrendingTvSeries() from db")
            val result = getTvSeriesListFromDb()
            result
        }
    }


    private suspend fun insertTvSeriesDetailsToDb(tvSeriesDetails: TvSeriesDetails) {
        appDatabase.tvSeriesDetailDao().insertTvSeriesDetail(tvSeriesDetails)
    }

    private suspend fun getTvSeriesDetailsFromDb(id: Int): TvSeriesDetails? {
        return appDatabase.tvSeriesDetailDao().getTvSeriesById(id)
    }

    private suspend fun getTvSeriesListFromDb(): TvSeriesList? {
        return appDatabase.tvSeriesListDao().getTvSeriesList()
    }

    private suspend fun insertTvSeriesListToDb(tvSeriesList: TvSeriesList) {
        appDatabase.tvSeriesListDao().insertTvSeriesList(tvSeriesList)
    }

}