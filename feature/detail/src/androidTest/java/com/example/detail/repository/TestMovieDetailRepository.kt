package com.example.detail.repository

import com.example.movie.model.Movie
import com.example.movie.model.MovieDetail
import com.example.movie.repository.movieDetail.MovieDetailRepository
import javax.inject.Inject

class TestMovieDetailRepository @Inject constructor(): MovieDetailRepository {

    override suspend fun getFavoriteMovie(id: Int): Movie? = null

    override suspend fun getDetails(moveID: Int): Result<MovieDetail> =
        Result.success(
            MovieDetail(
                adult = false,
                backdropPath = "",
                budget = 0,
                genreApis = emptyList(),
                homepage = "",
                id = 0,
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                revenue = 0L,
                runtime = 0,
                status = "",
                title = "",
                voteAverage = 0.0,
                voteCount = 0,
                isSelected = false
            )
        )
}