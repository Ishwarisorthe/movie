package com.example.popularmovietask.component

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.popularmovietask.R
import com.example.popularmovietask.utils.BaseViewModel

/*
 * Base class for activity
 **/
abstract class BaseActivity<B : ViewBinding, V : BaseViewModel> : AppCompatActivity() {
    protected lateinit var viewModel: V
    private lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding()
        setContentView(binding.root)
        viewModel = initViewModel()

        // To keep all activities portrait oriented only.
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    abstract fun initViewModel(): V

    abstract fun initBinding(): B


    protected fun replaceFragment(
        fragmentDestination: Int,
        @Nullable bundle: Bundle? = null,
        containerId: Int = R.id.fragment_container_view
    ) {
        Navigation.findNavController(this, containerId)
            .navigate(fragmentDestination, bundle)
    }
}