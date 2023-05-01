package com.example.popular.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.common.theme.MovieTheme
import com.example.movie.model.Movie
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PopularMovieScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun loadsTheDefaultResults() = runTest {
        composeRule.setContent {
            MovieTheme {
                val movies = flow {
                    emit(PagingData.from(listOf(Movie(id = 0))))
                }.collectAsLazyPagingItems()

                PopularMovie(
                    movies = movies,
                    onNavigateToDetails = {},
                    favoriteMovie = { _, _ -> }
                )
            }
        }
        composeRule.onNodeWithTag("popularRV").onChildren().assertCountEquals(1)
    }
}