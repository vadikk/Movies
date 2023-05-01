package com.example.detail.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.common.theme.MovieTheme
import com.example.detail.navigation.detailMovieNavRoute
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MovieDetailScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()

            MovieTheme {
                NavHost(
                    navController = navController,
                    startDestination = detailMovieNavRoute,
                    modifier = Modifier.testTag("testNavController")
                ) {
                    composable(
                        route = detailMovieNavRoute
                    ) {
                        MovieDetail(
                            state = MovieDetailState.Loading,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }

    @Test
    fun pressBackBtn_popBackStack() {
        composeRule.onNodeWithTag("onBackClick").performClick()

    }
}