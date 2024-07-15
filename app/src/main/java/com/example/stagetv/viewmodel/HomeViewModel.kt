package com.example.stagetv.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import com.example.stagetv.data.network.NetworkResult
import com.example.stagetv.data.repository.movie.MovieRepository
import com.example.stagetv.data.repository.tvseries.TvSeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvSeriesRepository: TvSeriesRepository,
) :
    ViewModel() {

    private val _isMovieFetchingProgress =
        MutableLiveData<NetworkResult<MoviesList?>>(NetworkResult.Unspecified())

    val isMovieFetchingProgress: LiveData<NetworkResult<MoviesList?>>
        get() = _isMovieFetchingProgress

    private val _isTvSeriesFetchingProgress =
        MutableLiveData<NetworkResult<TvSeriesList?>>(NetworkResult.Unspecified())

    val isTvSeriesFetchingProgress: LiveData<NetworkResult<TvSeriesList?>>
        get() = _isTvSeriesFetchingProgress


    fun getTrendingMovies() {
        _isMovieFetchingProgress.postValue(NetworkResult.Loading())
        viewModelScope.launch {
            try {
                val deferredResponse = async(Dispatchers.IO) {
                    movieRepository.getTrendingMovies()
                }
                val response = deferredResponse.await()
                if (response == null) {
                    _isMovieFetchingProgress.postValue(
                        NetworkResult.Failure(
                            null,
                            "Please try again"
                        )
                    )
                } else {
                    _isMovieFetchingProgress.postValue(
                        NetworkResult.Success(
                            response,
                            "Trending movie fetched successfully"
                        )
                    )
                    Log.d("Ninja HomeViewModel", "postValue response")
                }

            } catch (e: Exception) {
                _isMovieFetchingProgress.postValue(
                    NetworkResult.Failure(
                        null,
                        "Please try again"
                    )
                )
                Log.e("Ninja HomeViewModel", "error: ${e.message}")
            }
        }
    }

    fun getTrendingTvSeries() {
        _isTvSeriesFetchingProgress.postValue(NetworkResult.Loading())
        viewModelScope.launch {
            try {
                val deferredResponse = async(Dispatchers.IO) {
                    tvSeriesRepository.getTrendingTvSeries()
                }
                val response = deferredResponse.await()
                if (response == null) {
                    _isTvSeriesFetchingProgress.postValue(
                        NetworkResult.Failure(
                            null,
                            "Please try again"
                        )
                    )
                } else {
                    _isTvSeriesFetchingProgress.postValue(
                        NetworkResult.Success(
                            response,
                            "Trending Tv Series fetched successfully"
                        )
                    )
                    Log.d("Ninja HomeViewModel", "postValue response")
                }
            } catch (e: Exception) {
                _isTvSeriesFetchingProgress.postValue(
                    NetworkResult.Failure(
                        null,
                        "Please try again"
                    )
                )
                Log.e("Ninja HomeViewModel", "error: ${e.message}")
            }
        }
    }

    /*    fun getPopularMoviesList(): LiveData<PagingData<ItemThumbnail>> {
            return movieRepository.getPopularMoviesList()
        }*/

}