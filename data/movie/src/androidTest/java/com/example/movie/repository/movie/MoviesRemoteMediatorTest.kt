package com.example.movie.repository.movie

import androidx.paging.*
import com.example.database.MoviesDatabase
import com.example.database.dao.MovieRemoteKeysDao
import com.example.database.dao.MoviesDao
import com.example.database.model.MovieEntity
import com.example.database.utils.TransactionProvider
import com.example.movie.dataMovieTestModule
import com.example.network.NetworkDataSource
import com.example.network.fake.FakeNetworkSource
import com.example.network.model.MovieApi
import com.example.network.model.PopularMovieResponse
import com.example.testing.KoinTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent

@OptIn(
    ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class
)
class MoviesRemoteMediatorTest {

    @get:Rule
    val koinTestRule = KoinTestRule(listOf(dataMovieTestModule))

    private val networkDataSource by KoinJavaComponent.inject<NetworkDataSource>(
        NetworkDataSource::class.java,
        qualifier = named("Test")
    )
    private val movieDao by KoinJavaComponent.inject<MoviesDao>(MoviesDao::class.java)
    private val movieRemoteKeysDao by KoinJavaComponent.inject<MovieRemoteKeysDao>(
        MovieRemoteKeysDao::class.java
    )
    private val transactionProvider by KoinJavaComponent.inject<TransactionProvider>(
        TransactionProvider::class.java
    )
    private val db by KoinJavaComponent.inject<MoviesDatabase>(
        MoviesDatabase::class.java,
        qualifier = named("Test")
    )

    @After
    fun close() {
        db.close()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        (networkDataSource as FakeNetworkSource).addMoviesResult(
            Result.success(
                moviesResponseWithData
            )
        )

        val remoteMediator = MoviesRemoteMediator(
            networkDataSource, movieDao, movieRemoteKeysDao, transactionProvider
        )
        val pagingState = PagingState<Int, MovieEntity>(
            listOf(), null, PagingConfig(10), 10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        (networkDataSource as FakeNetworkSource).addMoviesResult(
            Result.success(
                moviesResponseWithNoData
            )
        )

        val remoteMediator = MoviesRemoteMediator(
            networkDataSource, movieDao, movieRemoteKeysDao, transactionProvider
        )
        val pagingState = PagingState<Int, MovieEntity>(
            listOf(), null, PagingConfig(10), 10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runTest {
        (networkDataSource as FakeNetworkSource).addMoviesResult(Result.failure(Throwable("Throw test failure")))

        val remoteMediator = MoviesRemoteMediator(
            networkDataSource, movieDao, movieRemoteKeysDao, transactionProvider
        )
        val pagingState = PagingState<Int, MovieEntity>(
            listOf(), null, PagingConfig(10), 10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }

}

internal val moviesResponseWithData = PopularMovieResponse(
    1, listOf(
        MovieApi(id = 1, page = 1),
        MovieApi(id = 2, page = 1),
        MovieApi(id = 3, page = 1),
        MovieApi(id = 4, page = 1),
        MovieApi(id = 5, page = 1),
        MovieApi(id = 6, page = 1),
        MovieApi(id = 7, page = 1),
        MovieApi(id = 8, page = 1),
        MovieApi(id = 9, page = 1),
        MovieApi(id = 10, page = 1),
        MovieApi(id = 11, page = 1),
        MovieApi(id = 12, page = 2),
        MovieApi(id = 13, page = 2),
    ), 2, 20
)

internal val moviesResponseWithNoData = PopularMovieResponse(
    1, emptyList(), 1, 0
)