package com.example.popularmovietask.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovietask.component.BaseActivity
import com.example.popularmovietask.databinding.ActivityMainBinding
import com.example.popularmovietask.viewmodel.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
/*
 * Main activity for all used fragments
 **/
class MainActivity :  BaseActivity<ActivityMainBinding, PopularMovieViewModel>() {

    override fun initViewModel() = ViewModelProvider(this)[PopularMovieViewModel::class.java]

    override fun initBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}