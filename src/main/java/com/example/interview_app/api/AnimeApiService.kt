package com.example.interview_app.api

import com.example.interview_app.Home.data.AnimeDetailResponse
import com.example.interview_app.Home.data.AnimeDetailsResponse
import com.example.interview_app.Home.data.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimeApiService {

    @GET("v4/top/anime")
    suspend fun getTopAnime():Response<AnimeResponse>

    @GET("v4/anime/{anime_id}/full")
    suspend fun getAnimeDetails(@Path("anime_id") animeId: String): Response<AnimeDetailsResponse>
}
