package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapp.service.model.FavoritesSingleton
import com.example.movieapp.service.model.Movie
import com.example.movieapp.service.model.MovieDetailResponse
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

    private val favoritesSingleton = FavoritesSingleton


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
                val movies = data?.search ?: mutableListOf()
                val updatedMovies = updateMoviesList(movies)
                _uiState.update {
                    it.copy(moviesList = updatedMovies as MutableList<Movie>)
                }
            } else {
                Log.e("Error","Unsuccessful call")
            }
        }
    }

    fun searchByMovie(imdbID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<MovieDetailResponse> =
                getRetrofit().create(APIService::class.java)
                    .getMovieDetail("?i=$imdbID&apikey=$API_KEY")
            val data: MovieDetailResponse? = call.body()
            if(call.isSuccessful){
                val plot = data?.plot ?: ""
                val director = data?.Director ?: ""
                _uiState.update {
                    it.copy(plotDetail = plot, directorDetail = director)
                }
            } else {
                Log.e("Error","Unsuccessful call")
            }
        }
    }

    fun updateMoviesList(movies: List<Movie>): List<Movie>{
        val updatedList = movies.map { movie ->
            if (movie in favoritesSingleton.favorites) {
                movie.copy(isFavorite = true)
            } else {
                movie
            }
        }
        return updatedList
    }

    fun updateIsFavorite(movie: Movie) {
        val moviesList = _uiState.value.moviesList
        val index: Int = moviesList.indexOf(movie)
        val newList = moviesList.toMutableList().apply {
            this[index] = movie.copy(isFavorite = !movie.isFavorite)
        }
        _uiState.update {
            it.copy(moviesList = newList)
        }
    }

    fun updateMovieDetail(movie: Movie) {
        _uiState.update {
            it.copy(movieDetail = movie)
        }
    }
}