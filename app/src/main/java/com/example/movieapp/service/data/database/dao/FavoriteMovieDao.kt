package com.example.movieapp.service.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.service.data.database.entities.FavoriteMovie

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movie")
    suspend fun getAllFavoriteMovieData():List<FavoriteMovie>
}
