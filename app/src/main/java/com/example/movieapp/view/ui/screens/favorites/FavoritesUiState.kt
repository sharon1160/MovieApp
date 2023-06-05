package com.example.movieapp.view.ui.screens.favorites

import com.example.movieapp.service.model.Movie

data class FavoritesUiState(
    val movieDetail: Movie = Movie(
        poster = "https://plantillasdememes.com/img/plantillas/imagen-no-disponible01601774755.jpg",
        title = "--",
        year = "--",
        imdbID = "--"
    ),
    val favoritesList: List<Movie> = emptyList()
)
