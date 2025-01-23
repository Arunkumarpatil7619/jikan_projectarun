package com.example.interview_app.Home.repo

import android.util.Log
import com.example.interview_app.Home.data.AnimeDetailResponse
import com.example.interview_app.Home.data.AnimeDetailsResponse
import com.example.interview_app.Home.data.AnimeResponse
import com.example.interview_app.api.AnimeApiService
import retrofit2.Response
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val apiservice:AnimeApiService){

    suspend fun getTopAnime() : Response<AnimeResponse> {
        return apiservice.getTopAnime()
    }


    suspend fun getAnimeDetails(animeId: Int) : Response<AnimeDetailsResponse> {
        Log.d("kiccha", "19999")
        return apiservice.getAnimeDetails("$animeId")
    }

}