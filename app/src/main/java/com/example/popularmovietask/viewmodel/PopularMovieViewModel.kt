package com.example.popularmovietask.viewmodel

import com.example.popularmovietask.usecase.UserValidationUseCase
import com.example.popularmovietask.usecase.PopularMovieUseCase
import com.example.popularmovietask.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase,
    private val emailUseCase: UserValidationUseCase
): BaseViewModel() {

    fun getPopularMovieUseCase() = popularMovieUseCase

    fun getEmailUseCase() = emailUseCase
}