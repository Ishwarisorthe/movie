package com.example.popularmovietask.view

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovietask.R
import com.example.popularmovietask.databinding.FragmentSplashBinding
import com.example.popularmovietask.utils.BaseFragment
import com.example.popularmovietask.viewmodel.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//Entry point fragment
class SplashFragment : BaseFragment<FragmentSplashBinding, PopularMovieViewModel>() {

    override fun initViewModel(): PopularMovieViewModel =
        ViewModelProvider(this).get(PopularMovieViewModel::class.java)

    override fun initBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun initViews() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateUser(R.id.userLoginFragment)
        }, 2000)
    }
}