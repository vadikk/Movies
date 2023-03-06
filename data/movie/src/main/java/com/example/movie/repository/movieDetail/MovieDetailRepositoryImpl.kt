package com.example.movie.repository.movieDetail

import com.example.database.dao.MovieFavoriteDao
import com.example.movie.model.Movie
import com.example.movie.model.MovieDetail
import com.example.movie.model.mapToDomain
import com.example.movie.model.toDomainModel
import com.example.network.NetworkDataSource

class MovieDetailRepositoryImpl(
    private val movieFavoriteDao: MovieFavoriteDao,
    private val networkDataSource: NetworkDataSource,
): MovieDetailRepository {

    override suspend fun getFavoriteMovie(id: Int): Movie? =
        movieFavoriteDao.getFavoriteMovie(id)?.toDomainModel()

    override suspend fun getDetails(moveID: Int): Result<MovieDetail> =
        networkDataSource.getDetails(moveID).map { it.mapToDomain() }
}