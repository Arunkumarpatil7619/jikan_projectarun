package com.example.interview_app.Home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interview_app.Home.data.Anime
import com.example.interview_app.Home.data.AnimeDetailResponse
import com.example.interview_app.Home.data.AnimeDetailsResponse
import com.example.interview_app.Home.repo.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

    private val _animeList = MutableLiveData<List<Anime>>()
    val animeList: LiveData<List<Anime>> get() = _animeList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _animeListdetails = MutableLiveData<AnimeDetailsResponse>()
    val animeList_detail: LiveData<AnimeDetailsResponse> get() = _animeListdetails

    fun fetchTopAnime() {
        viewModelScope.launch {
            try {
                val response = repository.getTopAnime()
                if (response.isSuccessful) {
                    _animeList.value = response.body()?.data ?: emptyList()
                } else {
                    _errorMessage.value = "Failed to fetch data: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Unknown error"
            }
        }
    }


    fun getAnimeDetails(id:Int){
        viewModelScope.launch {
            try {
                Log.d("kiccha", "1000")
                val response = repository.getAnimeDetails(id)
                Log.d("kiccha", "20")
                Log.d("kiccha_res", "0$response")
                _animeListdetails.value = response.body()
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Unknown error"
            }
        }

    }


}