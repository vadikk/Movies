package com.example.network.retrofit

import com.example.network.NetworkDataSource
import com.example.network.ext.parseResult
import com.example.network.model.MovieDetailApi
import com.example.network.model.PopularMovieResponse
import org.koin.java.KoinJavaComponent.inject

class RetrofitNetwork : NetworkDataSource {

    private val movieRetrofitApi by inject<MovieRetrofitApi>(MovieRetrofitApi::class.java)

    override suspend fun getPopularMovies(page: Int): Result<PopularMovieResponse> =
        movieRetrofitApi.getPopulars(page).parseResult()

    override suspend fun getDetails(moveID: Int): Result<MovieDetailApi> =
        movieRetrofitApi.getDetails(moveID).parseResult()
}