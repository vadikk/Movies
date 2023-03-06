package com.example.network.model

import com.squareup.moshi.Json

data class PopularMovieResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val movies: List<MovieApi> = emptyList(),
    @field:Json(name = "total_pages") val totalPage: Int,
    @field:Json(name = "total_results") val totalResults: Int
)
