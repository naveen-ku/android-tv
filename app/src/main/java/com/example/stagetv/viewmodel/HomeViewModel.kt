package com.example.stagetv.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stagetv.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val response = movieRepository.getTrendingMovies()
                Log.d("Ninja HomeViewModel", "response received")
            } catch (e: Exception) {
                Log.e("Ninja HomeViewModel", "error: ${e.message}")
            }
        }
    }
}