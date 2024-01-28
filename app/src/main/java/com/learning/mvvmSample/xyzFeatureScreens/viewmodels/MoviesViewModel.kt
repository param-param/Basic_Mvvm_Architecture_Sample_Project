package com.learning.mvvmSample.xyzFeatureScreens.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.mvvmSample.xyzFeatureScreens.models.Movie
import com.learning.mvvmSample.xyzFeatureScreens.models.MoviesResponse
import com.learning.mvvmSample.xyzFeatureScreens.repository.MoviesRepository
import com.learning.mvvmSample.xyzFeatureScreens.repository.ResponseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getMovies()
        }
    }

    val movies: LiveData<ResponseType<List<Movie>>>
        get() = repository.movies

}