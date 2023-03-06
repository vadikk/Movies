package com.example.network.model

import com.squareup.moshi.Json

data class GenreApi(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)