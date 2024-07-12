package com.example.stagetv.data.repository.movie

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import com.example.stagetv.data.network.MovieService
import com.example.stagetv.data.network.TvSeriesService
import com.example.stagetv.paging.MoviePagingSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val tvSeriesService: TvSeriesService
) {

    suspend fun getTrendingMovies(): MoviesList {
        val result = movieService.getTrendingMovies()
        Log.d("Ninja MovieRepository", "result: $result")
        return result
    }

    suspend fun getTrendingTvSeries(): TvSeriesList {
        val result = tvSeriesService.getTrendingTvSeries()
        Log.d("Ninja MovieRepository","result: $result")
        return result;
    }

    fun getPopularMoviesList() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 80),
        pagingSourceFactory = { MoviePagingSource(movieService) }).liveData
}