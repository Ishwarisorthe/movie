package com.example.popularmovietask.apiservices

import com.example.popularmovietask.BuildConfig
import com.example.popularmovietask.model.PopularMovie
import com.example.popularmovietask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMovieService {

    @GET(Constants.GET_POPULAR_MOVIES)
    suspend fun getPopularMovieList(
        @Query("language") language: String = "en",
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Response<PopularMovie>
}