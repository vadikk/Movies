package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.MovieFavoriteDao
import com.example.database.dao.MovieRemoteKeysDao
import com.example.database.dao.MoviesDao
import com.example.database.dao.ProfileDao
import com.example.database.model.MovieEntity
import com.example.database.model.MovieFavoriteEntity
import com.example.database.model.MovieRemoteKeys
import com.example.database.model.ProfileEntity

@Database(
    entities = [
        MovieEntity::class, MovieRemoteKeys::class,
        MovieFavoriteEntity::class, ProfileEntity::class],
    version = 1
)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
    abstract fun movieFavoriteDao(): MovieFavoriteDao
    abstract fun profileDao(): ProfileDao
}