package com.example.popularmovietask.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.popularmovietask.R
import com.example.popularmovietask.R.*

/*
 * Base fragment class
 **/
abstract class BaseFragment<B : ViewBinding, V : BaseViewModel> : Fragment() {

    protected lateinit var viewModel: V

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = initBinding()
        return binding.root
    }

    abstract fun initViewModel(): V

    abstract fun initBinding(): B

    abstract fun initViews()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hide Progress bar in case API call going on and we are trying to navigate user to next screen.
        // Otherwise it is keep on loading after navigating to next screen.
        viewModel.hideProgressBar()
        initViews()
        addBackPressCallback()
    }

    private fun addBackPressCallback() {
        doOnBackPress(viewLifecycleOwner) {
            onBackPress()
        }
    }

    // override to get hard back press callback
    open fun onBackPress() {
        with(findNavController()) {
            when (graph.startDestinationId) {
                currentDestination?.id, R.id.splashFragment -> requireActivity().finish()
                else -> popBackStack()
            }
        }
    }

    private fun doOnBackPress(
        viewLifecycleOwner: LifecycleOwner,
        onBackPress: (() -> Unit),
    ) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPress.invoke()
                }
            })
    }

    fun navigateUser(
        id: Int,
        bundle: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        findNavController().navigate(id, bundle, navOptions, navigatorExtras)
    }

    protected open fun statusBarColor() = color.white

}