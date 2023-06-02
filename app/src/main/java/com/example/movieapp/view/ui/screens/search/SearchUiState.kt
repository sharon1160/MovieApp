package com.example.movieapp.view.ui.screens.search

import com.example.movieapp.service.model.Movie

data class SearchUiState(
    val moviesList: MutableList<Movie> = mutableListOf(),
    val movieDetail: Movie = Movie(
        poster = "https://plantillasdememes.com/img/plantillas/imagen-no-disponible01601774755.jpg",
        title = "--",
        year = "--"
    )
)