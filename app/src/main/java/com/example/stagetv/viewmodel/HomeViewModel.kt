package com.example.stagetv.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList
import com.example.stagetv.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _trendingMovieList = MutableLiveData<MoviesList>(null)
    val trendingMovieList: LiveData<MoviesList>
        get() = _trendingMovieList

    private val _trendingTvSeriesList = MutableLiveData<TvSeriesList>(null)
    val trendingTvSeriesList: LiveData<TvSeriesList>
        get() = _trendingTvSeriesList

    private val _popularMoviesList = MutableLiveData<PagingData<ItemThumbnail>>(null)
    val popularMoviesList: LiveData<PagingData<ItemThumbnail>>
        get() = _popularMoviesList


    fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                val deferredResponse = async(Dispatchers.IO) {
                    movieRepository.getTrendingMovies()
                }
                val response = deferredResponse.await()
                _trendingMovieList.postValue(response)
                Log.d("Ninja HomeViewModel", "postValue response")
            } catch (e: Exception) {
                Log.e("Ninja HomeViewModel", "error: ${e.message}")
            }
        }
    }

    fun getTrendingTvSeries(){
        viewModelScope.launch {
            try {
                val deferredResponse = async(Dispatchers.IO) {
                    movieRepository.getTrendingTvSeries()
                }
                val response = deferredResponse.await()
                _trendingTvSeriesList.postValue(response)
                Log.d("Ninja HomeViewModel", "postValue response")
            } catch (e: Exception) {
                Log.e("Ninja HomeViewModel", "error: ${e.message}")
            }
        }
    }

    fun getPopularMoviesList(){
        movieRepository.getPopularMoviesList().observeForever{pagingData ->
            _popularMoviesList.postValue(pagingData)
        }
    }


}