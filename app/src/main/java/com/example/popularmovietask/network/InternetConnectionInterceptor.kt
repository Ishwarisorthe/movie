package com.example.popularmovietask.network

import okhttp3.Interceptor
import okhttp3.Response

abstract class InternetConnectionInterceptor : Interceptor {

    abstract fun isInternetConnectionAvailable(): Boolean

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        isInternetConnectionAvailable()
        return chain.proceed(request)
    }
}