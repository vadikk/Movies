package com.example.network.retrofit

import com.example.network.NetworkDataSource
import com.example.network.ext.parseResult
import com.example.network.model.MovieDetailApi
import com.example.network.model.PopularMovieResponse
import javax.inject.Inject

internal class RetrofitNetwork @Inject constructor(
    private val movieRetrofitApi: MovieRetrofitApi
): NetworkDataSource {

    override suspend fun getPopularMovies(page: Int): Result<PopularMovieResponse> =
        movieRetrofitApi.getPopulars(page).parseResult()

    override suspend fun getDetails(moveID: Int): Result<MovieDetailApi> =
        movieRetrofitApi.getDetails(moveID).parseResult()
}