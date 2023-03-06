package com.example.movie.repository.movie

import androidx.paging.PagingData
import com.example.movie.model.Movie
import com.example.movie.model.MovieDetail
import com.example.movie.model.PopularMovies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopulars(page: Int): Result<PopularMovies>

    suspend fun getDetails(moveID: Int): Result<MovieDetail>

    suspend fun saveToFavorite(movie: Movie)

    suspend fun deleteFromFavorite(id: Int)

    suspend fun getFavoriteMovie(id: Int): Movie?

    fun getPopularMovies(): Flow<PagingData<Movie>>

    fun favoriteMovies(): Flow<List<Movie>>
}