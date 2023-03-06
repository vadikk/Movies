package com.example.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.MovieEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("Select * From movies Order By page")
    fun getMoviesPagingSource(): PagingSource<Int, MovieEntity>

    @Query("Delete From movies")
    suspend fun clearAllMovies()
}