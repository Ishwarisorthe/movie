package com.example.popularmovietask.network

import android.system.ErrnoException
import com.example.popularmovietask.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.TimeUnit
import com.example.popularmovietask.BuildConfig


// Pinging to Google server fields
private const val GOOGLE_DNS_SERVER_IP = "8.8.8.8"
private const val GOOGLE_SERVER_PORT = 53
private const val INTERNET_CHECK_TIMEOUT = 15000
private const val RETROFIT_TIME_OUT = 90000

/*
 * ApiNetworkManager is used to allocate Retrofit clients for API call.
 **/
object ApiNetworkManager {

    val retrofitPopularMovieClientV1: Retrofit by lazy {
        getRetrofitClient(BuildConfig.BASE_URL)
    }

    private fun getRetrofitClient(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()

    private fun getHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().apply {
            connectTimeout(RETROFIT_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            readTimeout(RETROFIT_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            writeTimeout(RETROFIT_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            addInterceptor(object : InternetConnectionInterceptor() {
                override fun isInternetConnectionAvailable(): Boolean {
                    if (!isInternetAvailable()) {
                        throw NoInternetConnectivityException()
                    }
                    return true
                }
            })

            addInterceptor(interceptor)
        }.build()
    }

    private fun isInternetAvailable() = try {
        val socket = Socket()
        val socketAddress = InetSocketAddress(GOOGLE_DNS_SERVER_IP, GOOGLE_SERVER_PORT)
        socket.connect(socketAddress, INTERNET_CHECK_TIMEOUT)
        socket.close()
        true
    } catch (connectException: ConnectException) {
        false
    } catch (errnoException: ErrnoException) {
        false
    }
}

data class NoInternetConnectivityException(val errorMessage: String = "No internet connection") :
    IOException() {

    override fun getLocalizedMessage() = errorMessage
}