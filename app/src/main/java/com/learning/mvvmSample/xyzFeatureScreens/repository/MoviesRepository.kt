package com.learning.mvvmSample.xyzFeatureScreens.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.mvvmSample.xyzFeatureScreens.api.MoviesService
import com.learning.mvvmSample.xyzFeatureScreens.models.Movie
import com.learning.mvvmSample.xyzFeatureScreens.models.MoviesResponse
import com.learning.mvvmSample.xyzFeatureScreens.utils.Constants


class MoviesRepository(private val moviesService: MoviesService) {

    private val _moviesLiveData = MutableLiveData<ResponseType<List<Movie>>>()

    val movies: LiveData<ResponseType<List<Movie>>>
        get() = _moviesLiveData

    suspend fun getMovies() {
        val response = moviesService.getUpcomingMoviesList(Constants.API_KEY, 1)
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                _moviesLiveData.postValue(ResponseType.Success(responseBody.results))
                ResponseType.Success(responseBody)
            } else {
                _moviesLiveData.postValue(ResponseType.Error("Something went wrong"))
                ResponseType.Error("Something went wrong")
            }
        } else {
            _moviesLiveData.postValue(ResponseType.Error("Something went wrong"))
            ResponseType.Error("Something went wrong")
        }
    }

}







