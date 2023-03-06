package com.example.network.model

import com.squareup.moshi.Json

data class MovieDetailApi(
    @field:Json(name = "adult") val adult: Boolean = false,
    @field:Json(name = "backdrop_path") val backdropPath: String = "",
    @field:Json(name = "budget") val budget: Int = 0,
    @field:Json(name = "genres") val genreApis: List<GenreApi>? = emptyList(),
    @field:Json(name = "homepage") val homepage: String = "",
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_title") val originalTitle: String = "",
    @field:Json(name = "overview") val overview: String = "",
    @field:Json(name = "popularity") val popularity: Double = 0.0,
    @field:Json(name = "poster_path") val posterPath: String = "",
    @field:Json(name = "release_date") val releaseDate: String = "",
    @field:Json(name = "revenue") val revenue: Long = 0L,
    @field:Json(name = "runtime") val runtime: Int = 0,
    @field:Json(name = "status") val status: String = "",
    @field:Json(name = "title") val title: String = "",
    @field:Json(name = "vote_average") val voteAverage: Double = 0.0,
    @field:Json(name = "vote_count") val voteCount: Int = 0
)