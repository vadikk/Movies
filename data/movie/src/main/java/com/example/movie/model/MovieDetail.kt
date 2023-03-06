package com.example.movie.model

import com.example.network.model.MovieDetailApi

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genreApis: List<GenreMovie>,
    val homepage: String,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    var isSelected: Boolean
)

internal fun MovieDetailApi.mapToDomain(): MovieDetail = MovieDetail(
    adult,
    backdropPath,
    budget,
    genreApis?.map { it.toGenreMovie() }.orEmpty(),
    homepage,
    id,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    revenue,
    runtime,
    status,
    title,
    voteAverage,
    voteCount,
    isSelected = false
)
