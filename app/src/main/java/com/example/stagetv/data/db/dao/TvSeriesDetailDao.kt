package com.example.stagetv.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails

@Dao
interface TvSeriesDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeriesDetail(tvSeriesDetails: TvSeriesDetails)

    @Query("SELECT * FROM tv_series_details WHERE id = :mId")
    suspend fun getTvSeriesById(mId: Int): TvSeriesDetails?

    @Query("DELETE FROM tv_series_details")
    suspend fun deleteAllTvSeriesDetails()
}