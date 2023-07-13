package com.example.popularmovietask.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
 * Base view model class
 **/
abstract class BaseViewModel : ViewModel() {

    private val networkErrorLiveData = MutableLiveData<Boolean>()
    private val progressBarLiveData = MutableLiveData<String?>()

    fun showProgressBar(message: String?) {
        progressBarLiveData.value = message
    }

    fun hideProgressBar() {
        progressBarLiveData.value = null
    }

    fun showNetworkError() {
        networkErrorLiveData.value = true
    }

}