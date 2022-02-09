package com.saveoassignment.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.saveoassignment.infra.api.APIClient
import com.saveoassignment.infra.api.Resource
import com.saveoassignment.infra.api.ResponseHandler
import com.saveoassignment.model.MovieResponse
import com.saveoassignment.utils.Constant.API_KEY
import javax.inject.Inject

class Repository @Inject constructor(private val apiClient: APIClient) {

    private val responseHandler = ResponseHandler()

    fun getPageList() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            MoviePaging(apiClient)
        }
    ).liveData

    suspend fun getResponseFromAPI(page: Int): Resource<MovieResponse> {
        val response = apiClient.getResponseFromAPI(API_KEY, page)
        return try {
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}