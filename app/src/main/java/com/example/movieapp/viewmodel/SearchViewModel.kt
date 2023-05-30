package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapp.service.model.Movie
import com.example.movieapp.service.model.MovieResponse
import com.example.movieapp.service.repository.APIService
import com.example.movieapp.view.ui.screens.search.SearchUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "c284b078"
const val BASE_URL = "http://www.omdbapi.com/"

class SearchViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchByTitle(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<MovieResponse> =
                getRetrofit().create(APIService::class.java)
                    .getMoviesByTitle("?s=$query&apikey=$API_KEY")
            val data: MovieResponse? = call.body()
            if(call.isSuccessful){
                val movies = data?.search ?: emptyList()
                _uiState.update {
                    it.copy(moviesList = movies as MutableList<Movie>)
                }
            } else {
                Log.e("Error","Unsuccessful call")
            }
        }
    }
}