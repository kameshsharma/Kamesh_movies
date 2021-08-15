package com.example.lowesmovies.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ccom.example.lowesmovies.models.Result
import com.example.lowesmovies.backend.repository.MovieRepository
import com.example.lowesmovies.models.MovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Neha Kushwah on 3/29/21.
 * ViewModel for ListingActivity
 */
@HiltViewModel
class ListingViewModel @Inject constructor(private val moviesRepository: MovieRepository) :
    ViewModel() {

    private val _movieList = MutableLiveData<Result<MovieData?>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            moviesRepository.fetchMovies().collect {
                _movieList.value = it
            }
        }
    }
}