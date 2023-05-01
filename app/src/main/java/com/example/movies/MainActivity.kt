package com.example.movies

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.common.theme.MovieTheme
import com.example.movies.navigation.MovieAppState
import com.example.movies.navigation.MovieBottomNavBar
import com.example.movies.navigation.MovieNavHost
import com.example.movies.navigation.rememberMovieAppState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                MovieApp()
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun MovieApp(
        movieAppState: MovieAppState = rememberMovieAppState(
            navController = rememberAnimatedNavController()
        )
    ) {
        Scaffold(
            bottomBar = {
                if (movieAppState.shouldShowBottomBar) {
                    MovieBottomNavBar(
                        destinations = movieAppState.topLevelDestinations,
                        onNavigateToDestination = { movieAppState.navigateToTopLevelDestination(it) },
                        currentDestination = movieAppState.currentDestination,
                        modifier = Modifier.testTag("SetupBottomNavBar")
                    )
                }
            }
        ) { paddingValues ->
            MovieNavHost(
                navController = movieAppState.navController,
                onBackClick = movieAppState::onBackClick,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}