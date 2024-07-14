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
    private val appContext: Context
) {

    suspend fun getMovieDetails(id: Int): MovieDetails? {
        return if (NetworkUtils.isInternetAvailable(appContext)) {
            Log.d("Ninja MovieRepository", "online:: getMovieDetails() check DB")
            var result = getMovieDetailsFromDb(id)
            if (result == null) {
                Log.d("Ninja MovieRepository", "getMovieDetails() from network")
                result = movieService.getMovieDetail(id)
                insertMovieDetailsToDb(result)
            }
            result
        } else {
            Log.d("Ninja MovieRepository", "offline:: getMovieDetails() from db")
            val result = getMovieDetailsFromDb(id)
            result
        }
    }


    suspend fun getTrendingMovies(): MoviesList? {
        return if (NetworkUtils.isInternetAvailable(appContext)) {
            Log.d("Ninja MovieRepository", "online:: getTrendingMovies() check DB")
            var result = getMovieListFromDb()
            if (result == null) {
                Log.d("Ninja MovieRepository", "getTrendingMovies() from network")
                result = movieService.getTrendingMovies()
                insertMovieListToDb(result)
            }
            result
        } else {
            Log.d("Ninja MovieRepository", "offline:: getTrendingMovies() from db")
            val result = getMovieListFromDb()
            result
        }
    }


    fun getPopularMoviesList() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 80),
        pagingSourceFactory = { MoviePagingSource(movieService) }).liveData

    private suspend fun insertMovieDetailsToDb(movieDetails: MovieDetails) {
        appDatabase.movieDetailDao().insertMovieDetails(movieDetails)
    }

    private suspend fun getMovieDetailsFromDb(id: Int): MovieDetails? {
        return appDatabase.movieDetailDao().getMovieDetailById(id)
    }

    private suspend fun getMovieListFromDb(): MoviesList? {
        return appDatabase.movieListDao().getMovieList()
    }

    private suspend fun insertMovieListToDb(moviesList: MoviesList) {
        appDatabase.movieListDao().insertMovieList(moviesList)
    }
}