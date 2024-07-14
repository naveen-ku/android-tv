package com.example.stagetv.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList

@Dao
interface TvSeriesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeriesList(moviesList: TvSeriesList)

    @Query("DELETE FROM tv_series")
    suspend fun deleteAllTvSeries()

    @Query("SELECT * FROM tv_series")
    suspend fun getTvSeriesList(): TvSeriesList?
}