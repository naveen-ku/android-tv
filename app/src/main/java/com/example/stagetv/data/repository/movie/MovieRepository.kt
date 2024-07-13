package com.example.stagetv.data.repository.movie

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.db.entity.movie.MovieDetails
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import com.example.stagetv.data.network.MovieService
import com.example.stagetv.data.network.TvSeriesService
import com.example.stagetv.paging.MoviePagingSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val tvSeriesService: TvSeriesService,
    private val appDatabase: AppDatabase
) {

    suspend fun getMovieDetails(id:Int): MovieDetails {
        val result = movieService.getMovieDetail(id)
        Log.d("Ninja MovieRepository","getMovieDetails $result")
        return result
    }

    suspend fun getTvSeriesDetails(id:Int): TvSeriesDetails {
        val result = tvSeriesService.getTvSeriesDetail(id)
        Log.d("Ninja MovieRepository","getTvSeriesDetails $result")
        return result
    }

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

    suspend fun insertMovieDetailsToDb(movieDetails: MovieDetails){
        appDatabase.movieDetailDao().insertMovieDetails(movieDetails)
    }
}