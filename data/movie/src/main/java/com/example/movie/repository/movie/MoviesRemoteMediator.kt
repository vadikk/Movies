package com.example.movie.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.database.dao.MovieRemoteKeysDao
import com.example.database.dao.MoviesDao
import com.example.database.model.MovieEntity
import com.example.database.model.MovieRemoteKeys
import com.example.database.utils.TransactionProvider
import com.example.movie.model.toDatabaseModel
import com.example.network.NetworkDataSource
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
internal class MoviesRemoteMediator(
    private val networkDataSource: NetworkDataSource,
    private val moviesDao: MoviesDao,
    private val movieRemoteKeysDao: MovieRemoteKeysDao,
    private val transactionProvider: TransactionProvider
): RemoteMediator<Int, MovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return if (System.currentTimeMillis() - (movieRemoteKeysDao.getCreationTime() ?: 0) < cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = networkDataSource.getPopularMovies(page = page)

            if (apiResponse.isFailure)
                return MediatorResult.Error(apiResponse.exceptionOrNull() ?: Throwable("Api failure"))

            val movies = apiResponse.getOrNull()?.movies.orEmpty()
            val endOfPaginationReached = movies.isEmpty()

            transactionProvider.runAsTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieRemoteKeysDao.clearRemoteKeys()
                    moviesDao.clearAllMovies()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = movies.map {
                    MovieRemoteKeys(movieID = it.id, prevKey = prevKey, currentPage = page, nextKey = nextKey)
                }
                movieRemoteKeysDao.insertAll(remoteKeys)
                moviesDao.insertAll(movies.map { it.toDatabaseModel() }.onEachIndexed { _, movie -> movie.page = page })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieEntity>): MovieRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                movieRemoteKeysDao.getRemoteKeyByMovieID(id)
            }
        }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): MovieRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            movieRemoteKeysDao.getRemoteKeyByMovieID(movie.id)
        }
    }
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): MovieRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            movieRemoteKeysDao.getRemoteKeyByMovieID(movie.id)
        }
    }

}