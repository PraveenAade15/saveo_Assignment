package com.saveoassignment.infra.api

import com.saveoassignment.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {
    @GET("movie/popular")
    suspend fun getResponseFromAPI(
        @Query("api_key") api_key : String,
        @Query("page") page:Int
    ) : MovieResponse
}