package com.example.stagetv.data.repository.movie

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.db.entity.movie.MovieDetails
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.network.MovieService
import com.example.stagetv.paging.MoviePagingSource
import com.example.stagetv.utils.NetworkUtils
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val appDatabase: AppDatabase,
) {

    suspend fun getMovieDetails(id: Int, context: Context): MovieDetails {
        if (NetworkUtils.isInternetAvailable(context)) {
            Log.d("Ninja MovieRepository", "getMovieDetails() from network")
            val result = movieService.getMovieDetail(id)
            insertMovieDetailsToDb(result)
            return result
        } else {
            Log.d("Ninja MovieRepository", "getMovieDetails() from db")
            val result = appDatabase.movieDetailDao().getMovieDetailById(id)
            return result!!
        }
    }


    suspend fun getTrendingMovies(): MoviesList {
        val result = movieService.getTrendingMovies()
        Log.d("Ninja MovieRepository", "result: $result")
        return result
    }


    fun getPopularMoviesList() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 80),
        pagingSourceFactory = { MoviePagingSource(movieService) }).liveData

    private suspend fun insertMovieDetailsToDb(movieDetails: MovieDetails) {
        appDatabase.movieDetailDao().insertMovieDetails(movieDetails)
    }
}