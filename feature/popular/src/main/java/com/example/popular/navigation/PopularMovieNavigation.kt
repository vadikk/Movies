package com.example.popular.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.popular.presentation.PopularMovieScreen
import com.google.accompanist.navigation.animation.composable

private const val popularMovieGraphRoute = "popular_graph"
const val popularMovieNavRoute = "popular_movie_route"

fun NavHostController.navigateToPopularMovie(navOptions: NavOptions? = null) {
    this.navigate(popularMovieNavRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.popularMovieScreen(
    onNavigateToDetails: (id: Int) -> Unit
) {
    composable(route = popularMovieNavRoute) {
        PopularMovieScreen(
            onNavigateToDetails = { movieID -> onNavigateToDetails(movieID) }
        )
    }
}