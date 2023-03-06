package com.example.movie.model

import com.example.network.model.GenreApi

data class GenreMovie(
    val id: Int,
    val name: String
)

internal fun GenreApi.toGenreMovie() = GenreMovie(id, name)
