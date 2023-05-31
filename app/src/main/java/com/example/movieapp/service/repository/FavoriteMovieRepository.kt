package com.example.movieapp.service.repository

import com.example.movieapp.service.data.database.dao.FavoriteMovieDao
import com.example.movieapp.service.data.database.entities.FavoriteMovie
import com.example.movieapp.service.model.Movie

class FavoriteMovieRepository(
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun insert(movie: Movie) {
        val entity = FavoriteMovie(poster = movie.poster, title = movie.title, year = movie.year)
        favoriteMovieDao.insert(entity)
    }

    suspend fun getAllFavoriteMovieData(): List<Movie> {
        val entities = favoriteMovieDao.getAllFavoriteMovieData()
        return entities.map {
            Movie(poster = it.poster, title = it.title, year = it.year, isFavorite = true)
        }
    }


    /*
    companion object {
        var favoriteMovieDatabase: FavoriteMovieDatabase? = null

        private fun initializeDB(context: Context): FavoriteMovieDatabase?
        {
            return FavoriteMovieDatabase.getInstance(context)!!
        }

        fun insert(context: Context,favoriteMovie: FavoriteMovie)
        {
            favoriteMovieDatabase= initializeDB(context)

            CoroutineScope(IO).launch {
                favoriteMovieDatabase!!.favoriteMovieDao().insert(favoriteMovie)
            }

            Log.e("eeee","${favoriteMovie}")
        }

        fun getAllFavoriteMovieData(context: Context): List<FavoriteMovie>
        {
            favoriteMovieDatabase= initializeDB(context)
            var query: List<FavoriteMovie> = listOf()
            CoroutineScope(IO).launch {
                query = favoriteMovieDatabase!!.favoriteMovieDao().getAllFavoriteMovieData()
            }
            return query
        }
    }*/
}
