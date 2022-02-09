package com.saveoassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saveoassignment.infra.api.Resource
import com.saveoassignment.model.MovieResponse
import com.saveoassignment.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getMovieResponse() = repository.getPageList()

    fun getResponseFromApi(page: Int): LiveData<Resource<MovieResponse>> {
        return liveData(Dispatchers.IO) {
            val response = repository.getResponseFromAPI(page)
            emit(response)
        }
    }
}