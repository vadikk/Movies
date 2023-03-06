package com.example.network.fake

import com.example.network.NetworkDataSource
import com.example.network.model.MovieDetailApi
import com.example.network.model.PopularMovieResponse

class FakeNetworkSource : NetworkDataSource {

    private var moviesResult: Result<PopularMovieResponse>? = null

    fun addMoviesResult(moviesResult: Result<PopularMovieResponse>){
        this.moviesResult = moviesResult
    }

    override suspend fun getPopularMovies(page: Int): Result<PopularMovieResponse> =
        moviesResult ?: Result.success(
            PopularMovieResponse(
                1,
                emptyList(),
                1, 0
            )
        )

    override suspend fun getDetails(moveID: Int): Result<MovieDetailApi> =
        Result.success(MovieDetailApi(id = 1))
}