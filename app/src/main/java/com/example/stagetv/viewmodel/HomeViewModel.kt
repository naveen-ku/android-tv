package com.example.stagetv.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.stagetv.data.db.entity.movie.MovieThumbnail
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _movieList = MutableLiveData<MoviesList>(null)
    val movieList: LiveData<MoviesList>
        get() = _movieList

    fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                val deferredResponse = async(Dispatchers.IO) {
                    movieRepository.getTrendingMovies()
                }
                val response = deferredResponse.await()
                _movieList.postValue(response)
                Log.d("Ninja HomeViewModel", "postValue response")
            } catch (e: Exception) {
                Log.e("Ninja HomeViewModel", "error: ${e.message}")
            }
        }
    }

    fun getMoviesList(): LiveData<PagingData<MovieThumbnail>> {
        val list = movieRepository.getMoviesList().cachedIn(viewModelScope)
        return list
    }
}