package com.example.popularmovietask.network

import android.app.Activity
import android.widget.Toast
import com.example.popularmovietask.R
import com.example.popularmovietask.utils.BaseViewModel
import com.example.popularmovietask.utils.Constants

/*
* Generic Error handler for showing API response errors.
* */
object ApiErrorHandler {

    private const val TAG = "#### Error Log : "

    fun onAppLoader(
        activity: Activity,
        viewModel: BaseViewModel,
        message: String = Constants.PROGRESS_BAR_DEFAULT_MESSAGE,
    ) {
        viewModel.showProgressBar(message)
    }

    fun onInvalidResponse(
        activity: Activity,
        message: String = activity.getString(R.string.something_went_wrong),
    ) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun onHideScreenLoader(
        activity: Activity,
        viewModel: BaseViewModel,
    ) {
        viewModel.hideProgressBar()
    }

    /*
    * Handles DataState.Error response.
    * */
    fun onErrorResponse(error: Any?, activity: Activity) {
        Toast.makeText(activity, R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
    }

}