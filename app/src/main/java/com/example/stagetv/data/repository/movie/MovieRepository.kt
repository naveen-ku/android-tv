package com.example.stagetv.data.repository.movie

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.db.entity.ItemThumbnail
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

    /**
     * @param id Unique Id of movie
     * @return Details of Movie or null
     * */
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


    /**
     * @return Top 20 Trending movies on TMDB
     * */
    suspend fun getTrendingMovies(): MoviesList? {
        return if (NetworkUtils.isInternetAvailable(appContext)) {
            Log.d("Ninja MovieRepository", "getTrendingMovies() from network")
            try {
                val result = movieService.getTrendingMovies()
                insertMovieListToDb(result)
                result
            } catch (e: Exception) {
                Log.e("Ninja MovieRepository", "getTrendingMovies() error: ${e.message}")
                null
            }
        } else {
            Log.d("Ninja MovieRepository", "offline:: getTrendingMovies() from db")
            val result = getMovieListFromDb()
            result
        }
    }

    /**
     * It's paginated API
     * @return  LiveData<PagingData<ItemThumbnail>>
     * */

    fun getPopularMoviesList(): LiveData<PagingData<ItemThumbnail>> = Pager(
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