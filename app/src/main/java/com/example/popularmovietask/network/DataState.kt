package com.example.popularmovietask.network

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error<out T>(val error: Any?) : DataState<T>()
    data class NetworkExceptions<out T>(val exception: Exception?) : DataState<T>()
    object NullResponse : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
