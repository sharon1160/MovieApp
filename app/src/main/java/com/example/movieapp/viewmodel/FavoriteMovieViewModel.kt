package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.service.model.Movie
import com.example.movieapp.service.repository.FavoriteMovieRepository
import com.example.movieapp.view.ui.screens.favorites.FavoritesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(favoritesList = favoriteMovieRepository.getAllFavoriteMovieData())
            }
        }
    }

    fun insert(movie: Movie) {
        viewModelScope.launch {
            favoriteMovieRepository.insert(movie)
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            favoriteMovieRepository.deleteMovie(movie)
        }
    }

    fun getAllFavoriteMovieData(): List<Movie> {
        viewModelScope.launch {
            _uiState.update {
                it.copy(favoritesList = favoriteMovieRepository.getAllFavoriteMovieData())
            }
        }
        return uiState.value.favoritesList
    }

    fun deleteAllFavoriteMovieData() {
        viewModelScope.launch {
            favoriteMovieRepository.deleteAllFavoriteMovieData()
            _uiState.update {
                it.copy(favoritesList = favoriteMovieRepository.getAllFavoriteMovieData())
            }
        }
    }
}
