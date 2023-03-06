package com.example.movie.repository.movieDetail

import com.example.movie.model.Movie
import com.example.movie.model.MovieDetail

interface MovieDetailRepository {

    suspend fun getFavoriteMovie(id: Int): Movie?

    suspend fun getDetails(moveID: Int): Result<MovieDetail>

}