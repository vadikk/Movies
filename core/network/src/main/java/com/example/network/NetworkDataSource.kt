package com.example.network

import com.example.network.model.MovieDetailApi
import com.example.network.model.PopularMovieResponse
import retrofit2.http.Path

interface NetworkDataSource {

    suspend fun getPopularMovies(page: Int = 0): Result<PopularMovieResponse>

    suspend fun getDetails(moveID: Int): Result<MovieDetailApi>
}