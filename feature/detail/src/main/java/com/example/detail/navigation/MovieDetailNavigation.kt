package com.example.detail.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.common.util.enterTransition
import com.example.common.util.exitTransition
import com.example.common.util.popEnterTransition
import com.example.common.util.popExitTransition
import com.example.detail.presentation.MovieDetailScreen
import com.google.accompanist.navigation.animation.*

const val detailMovieNavRoute = "detail_movie_route"
internal const val movieIdArg = "movieID"

fun NavHostController.navigateToDetailMovie(
    movieID: String,
    navOptions: NavOptions? = null
) {
    navigate("$detailMovieNavRoute/$movieID", navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.detailMovieScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$detailMovieNavRoute/{$movieIdArg}",
        arguments = listOf(
            navArgument(movieIdArg) { type = NavType.StringType }
        ),
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition }
    ) {
        MovieDetailScreen(onBackClick)
    }
}