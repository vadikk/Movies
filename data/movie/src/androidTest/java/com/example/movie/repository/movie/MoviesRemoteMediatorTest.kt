package com.example.movie.repository.movie

import androidx.paging.*
import com.example.database.MoviesDatabase
import com.example.database.dao.MovieRemoteKeysDao
import com.example.database.dao.MoviesDao
import com.example.database.model.MovieEntity
import com.example.database.utils.TransactionProvider
import com.example.network.NetworkDataSource
import com.example.network.fake.FakeNetworkSource
import com.example.network.model.MovieApi
import com.example.network.model.PopularMovieResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(
    ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class
)
@HiltAndroidTest
class MoviesRemoteMediatorTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: MoviesDatabase

    @Inject
    lateinit var transactionProvider: TransactionProvider

    @Inject
    lateinit var movieDao: MoviesDao

    @Inject
    lateinit var movieRemoteKeysDao: MovieRemoteKeysDao
    private lateinit var networkDataSource: NetworkDataSource

    @Before
    fun setup() {
        hiltRule.inject()
        networkDataSource = FakeNetworkSource()
    }

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