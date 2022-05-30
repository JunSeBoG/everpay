package com.junsebog.everpay.di

import com.google.gson.GsonBuilder
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.data.remote.EvertecApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    fun providesEvertecApi(retrofit: Retrofit): EvertecApi = retrofit.create(EvertecApi::class.java)
}