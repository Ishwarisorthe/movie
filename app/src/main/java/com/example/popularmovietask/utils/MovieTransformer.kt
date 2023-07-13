package com.example.popularmovietask.utils

import com.example.popularmovietask.model.Movie
import com.example.popularmovietask.model.PopularMovie
import javax.inject.Inject

/*
 * This class transform the popular movie response to required model
 */
class MovieTransformer @Inject constructor(
) {
    fun transformPopularMovieResponseToModel(popularMovieList: PopularMovie): List<Movie> {
        val movieList =
            arrayListOf<Movie>()
        popularMovieList.let {
            if (it.results.isNotEmpty()) {
                it.results.forEach { item ->
                    val paymentItem = Movie(
                        title = item.title,
                        imgUrl = item.posterPath,
                    )
                    movieList.add(paymentItem)
                }
            }
        }
        return movieList
    }

}