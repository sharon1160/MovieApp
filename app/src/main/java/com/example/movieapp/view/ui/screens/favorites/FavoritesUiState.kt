package com.example.movieapp.view.ui.screens.favorites

import com.example.movieapp.service.model.Movie

data class FavoritesUiState(
    val favoritesList: List<Movie> = emptyList()
)
