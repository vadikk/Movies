package com.example.movie.model

import com.example.network.model.PopularMovieResponse

data class PopularMovies(
    val page: Int = 0,
    val movies: List<Movie> = emptyList(),
    val totalPage: Int = 0,
    val totalResults: Int = 0
)

internal fun PopularMovieResponse.mapToPopularMovies() =
    PopularMovies(page, movies.map { it.toDomainModel() }, totalPage, totalResults)