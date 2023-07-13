package com.example.popularmovietask.network

import android.util.Log
import retrofit2.Response

/*
* API response handler, it triggers respective data state of livedata based response received from API.
* This class should be extended by repository, if it is calling any API.
* */
abstract class ApiHandler {
    /*
    * Generic method for safe API call for v1 APIs.
    * */
    suspend fun <T> apiCallManager(apiCall: suspend () -> Response<T>): DataState<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return DataState.Success(it)
                }
                return DataState.NullResponse
            }
            return DataState.Error(response.errorBody())
        } catch (exception: Exception) {
            Log.e("API Handler Exception", exception.message!!)
            return DataState.NetworkExceptions(exception)
        }
    }
}