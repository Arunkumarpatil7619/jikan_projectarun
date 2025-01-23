package com.example.interview_app.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkApi {
    private lateinit var retrofit:Retrofit

    private const val BASE_URL = "https://api.jikan.moe/"
    private val loggingInterceptor=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }
    private val clint=OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
         retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).client(clint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideJikanApi(): AnimeApiService {
        provideRetrofit()
        return retrofit.create(AnimeApiService::class.java)
    }
}
