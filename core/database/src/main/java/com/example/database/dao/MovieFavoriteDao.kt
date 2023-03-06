package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.MovieFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: MovieFavoriteEntity)

    @Query("DELETE FROM favorite_movies WHERE id LIKE :id")
    suspend fun deleteMovie(id: Int)

    @Query("SELECT * FROM favorite_movies WHERE id LIKE :id")
    suspend fun getFavoriteMovie(id: Int): MovieFavoriteEntity?

    @Query("Select * From favorite_movies")
    suspend fun getFavoriteMovies(): List<MovieFavoriteEntity>

    @Query("Select * From favorite_movies")
    fun favoriteMovies(): Flow<List<MovieFavoriteEntity>>
}