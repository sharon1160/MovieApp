package com.example.movieapp.service.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.service.data.database.dao.FavoriteMovieDao
import com.example.movieapp.service.data.database.entities.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class FavoriteMovieDatabase: RoomDatabase() {
   abstract val dao: FavoriteMovieDao
}
