package com.example.movieapp.service.model

object FavoritesSingleton {
    var favorites = mutableListOf<Movie>()

    fun addMovie(movie: Movie) {
        favorites.add(movie)
    }

    fun removeMovie(movie: Movie) {
        favorites = favorites.filter{ it.title != movie.title } as MutableList<Movie>
    }

    fun clear() {
        favorites = mutableListOf<Movie>()
    }
}