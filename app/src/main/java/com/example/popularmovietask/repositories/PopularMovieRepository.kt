package com.example.popularmovietask.repositories

import com.example.popularmovietask.apiservices.PopularMovieService
import com.example.popularmovietask.network.ApiHandler
import javax.inject.Inject

/*
 * Repository class for multiple apis call
 **/
class PopularMovieRepository @Inject constructor(
    private val popularMovieService: PopularMovieService
) : ApiHandler() {

    // suspend api call to get list of movies
    suspend fun getPopularMovieList(pageNo: Int) = apiCallManager { popularMovieService.getPopularMovieList(page = pageNo) }
}