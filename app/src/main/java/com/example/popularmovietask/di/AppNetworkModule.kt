package com.example.popularmovietask.di

import com.example.popularmovietask.apiservices.PopularMovieService
import com.example.popularmovietask.network.ApiNetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppNetworkModule {

    @Singleton
    @Provides
    @RetrofitMovieClient
    fun provideRetrofitCubeClientV1() = ApiNetworkManager.retrofitPopularMovieClientV1


    @Singleton
    @Provides
    fun providePopularMovieService(@RetrofitMovieClient retrofit: Retrofit) =
        retrofit.create(PopularMovieService::class.java)

}
