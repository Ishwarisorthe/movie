package com.example.popularmovietask.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularmovietask.adapters.MovieItemProvider
import com.example.popularmovietask.component.BaseItem
import com.example.popularmovietask.component.BaseItemPaginationAdapter
import com.example.popularmovietask.component.PaginationScrollListener
import com.example.popularmovietask.databinding.FragmentPopularMovieListBinding
import com.example.popularmovietask.network.ApiErrorHandler
import com.example.popularmovietask.network.DataState
import com.example.popularmovietask.usecase.PopularMovieUseCase
import com.example.popularmovietask.utils.BaseFragment
import com.example.popularmovietask.viewmodel.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.os.Handler
import android.os.Looper
import com.example.popularmovietask.ktx.toast

@AndroidEntryPoint
class PopularMovieListFragment :
    BaseFragment<FragmentPopularMovieListBinding, PopularMovieViewModel>() {
    private lateinit var popularMovieUseCase: PopularMovieUseCase
    private lateinit var movieAdapter: BaseItemPaginationAdapter
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var totalPages: Int = 1
    private val pageStart: Int = 1
    private var currentPage: Int = pageStart
    private var previousPage: Int = pageStart


    override fun initViewModel(): PopularMovieViewModel =
        ViewModelProvider(this)[PopularMovieViewModel::class.java]

    override fun initBinding(): FragmentPopularMovieListBinding =
        FragmentPopularMovieListBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMovieUseCase = viewModel.getPopularMovieUseCase()
    }

    override fun initViews() {
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            movieAdapter = BaseItemPaginationAdapter(MovieItemProvider { _, _ ->
            })
            adapter = movieAdapter
        }
        getPopularMovieList()
        addOnScrollListener()
    }

    // Implemented the recycler view scroll listener
    private fun addOnScrollListener() {
        binding.rvMovies.addOnScrollListener(object :
            PaginationScrollListener(binding.rvMovies.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1

                Handler(Looper.myLooper()!!).postDelayed({
                    loadPage()
                }, 1000)

            }
            override fun isLastPage(): Boolean {
                return isLastPage
            }
            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    private fun loadPage() {
        if (view != null) {
            getPopularMovieList()
        }
    }

    //request the movie list as per current page number
    private fun getPopularMovieList() {
        popularMovieUseCase.getPopularMovieList(currentPage)
        popularMovieUseCase.popularMovieLiveData.observe(viewLifecycleOwner) { response ->
            viewModel.hideProgressBar()
            when (response) {
                is DataState.Loading ->
                    ApiErrorHandler.onAppLoader(
                        requireActivity(),
                        viewModel,
                    )
                is DataState.Success -> {
                    isLoading = false
                    totalPages = response.data.totalPages!!
                    movieAdapter.addItems(popularMovieUseCase.getPopularTransformList(response.data) as MutableList<BaseItem>)
                    if (currentPage <= totalPages)
                        requireActivity().toast("Load page: $currentPage")
                }
                is DataState.Error -> ApiErrorHandler.onErrorResponse(
                    response.error,
                    requireActivity()
                )
                is DataState.NetworkExceptions -> viewModel.showNetworkError()
                else ->
                    Log.e("Error", "Unable to load content")
            }
        }
    }
}