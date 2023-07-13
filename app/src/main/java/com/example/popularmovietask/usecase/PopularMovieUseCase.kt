package com.example.popularmovietask.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.popularmovietask.model.Movie
import com.example.popularmovietask.model.PopularMovie
import com.example.popularmovietask.network.DataState
import com.example.popularmovietask.repositories.PopularMovieRepository
import com.example.popularmovietask.utils.MovieTransformer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Use case class to get movie data and transform the data
 **/
class PopularMovieUseCase @Inject constructor(
    private val popularMovieRepository: PopularMovieRepository,
    private val movieTransformer: MovieTransformer
) {

    private val popularMoviesLiveDataInternal =
        MutableLiveData<DataState<PopularMovie>>()
    val popularMovieLiveData: LiveData<DataState<PopularMovie>> = popularMoviesLiveDataInternal

    fun getPopularMovieList(pageNo: Int) {
        popularMoviesLiveDataInternal.postValue(DataState.Loading)
        CoroutineScope(Dispatchers.IO).launch {
            popularMoviesLiveDataInternal.postValue(
                popularMovieRepository.getPopularMovieList(pageNo)
            )
        }
    }

    fun getPopularTransformList(popularMovies: PopularMovie): List<Movie> =
        movieTransformer.transformPopularMovieResponseToModel(popularMovies)

}