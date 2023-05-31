package com.example.movieapp.service.model

import android.os.Build
import androidx.annotation.RequiresApi

object FavoritesSingleton {
    var favorites = mutableListOf<Movie>()

    fun addMovie(movie: Movie) {
        favorites.add(movie)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeMovie(movie: Movie) {
        favorites.removeIf { it.title == movie.title }
    }

    fun clear() {
        favorites = mutableListOf<Movie>()
    }
}