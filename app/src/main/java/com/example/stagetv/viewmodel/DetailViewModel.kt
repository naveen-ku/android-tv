package com.example.stagetv.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.stagetv.data.db.entity.movie.MovieDetails
import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails
import com.example.stagetv.data.repository.movie.MovieRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class DetailViewModel @AssistedInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted private val id: Int,
    @Assisted private val mediaType: String
) : ViewModel() {
    @AssistedFactory
    interface DetailViewModelFactory {
        fun create(id: Int, mediaType: String): DetailViewModel
    }

    // Suppressing unchecked cast warning
    @Suppress("UNCHECKED_CAST")
    companion object {

        fun providesFactory(
            assistedFactory: DetailViewModelFactory,
            id: Int,
            mediaType: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(id, mediaType) as T
            }
        }
    }

    private val _movieDetails = MutableLiveData<MovieDetails>(null)
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    private val _tvSeriesDetails = MutableLiveData<TvSeriesDetails>(null)
    val tvSeriesDetails: LiveData<TvSeriesDetails>
        get() = _tvSeriesDetails


    init {
        Log.d("Ninja HomeViewModel", "getMovieSeriesDetail() $id $mediaType")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (mediaType == "movie") {
                    val deferredResult = async { movieRepository.getMovieDetails(id) }
                    val result = deferredResult.await()
                    movieRepository.insertMovieDetailsToDb(result)
                    _movieDetails.postValue(result)
                } else {
                    val result = movieRepository.getTvSeriesDetails(id)
                    _tvSeriesDetails.postValue(result)
                }
            } catch (e: Exception) {
                Log.d("Ninja HomeViewModel", "error $e")
            }
        }
    }

}