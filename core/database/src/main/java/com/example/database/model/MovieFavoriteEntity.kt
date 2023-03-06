package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieFavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = ""
)
