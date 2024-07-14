package com.example.stagetv.viewmodel.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stagetv.data.db.entity.movie.MovieDetails
import com.example.stagetv.data.db.entity.tvseries.TvSeriesDetails
import com.example.stagetv.data.repository.movie.MovieRepository
import com.example.stagetv.data.repository.tvseries.TvSeriesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    private val movieRepository: MovieRepository,
    private val tvSeriesRepository: TvSeriesRepository,
    @Assisted private val id: Int,
    @Assisted private val mediaType: String,
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails?>(null)
    val movieDetails: LiveData<MovieDetails?>
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
                    _movieDetails.postValue(result)
                } else {
                    val result = tvSeriesRepository.getTvSeriesDetails(id)
                    _tvSeriesDetails.postValue(result)
                }
            } catch (e: Exception) {
                Log.d("Ninja HomeViewModel", "error $e")
            }
        }
    }

}