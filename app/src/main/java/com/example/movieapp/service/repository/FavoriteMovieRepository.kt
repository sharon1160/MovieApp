package com.example.movieapp.service.repository

import com.example.movieapp.service.data.database.dao.FavoriteMovieDao
import com.example.movieapp.service.data.database.entities.FavoriteMovie
import com.example.movieapp.service.model.Movie

class FavoriteMovieRepository(
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun insert(movie: Movie) {
        val entity = FavoriteMovie(poster = movie.poster, title = movie.title, year = movie.year, imdbID = movie.imdbID)
        favoriteMovieDao.insert(entity)
    }

    suspend fun deleteMovie(movie: Movie) {
        favoriteMovieDao.deleteMovieData(movie.title)
    }

    suspend fun getAllFavoriteMovieData(): List<Movie> {
        val entities = favoriteMovieDao.getAllFavoriteMovieData()
        return entities.map {
            Movie(poster = it.poster, title = it.title, year = it.year, isFavorite = true, imdbID = it.imdbID)
        }
    }

    suspend fun deleteAllFavoriteMovieData() {
        favoriteMovieDao.deleteAllFavoriteMovieData()
    }
}
