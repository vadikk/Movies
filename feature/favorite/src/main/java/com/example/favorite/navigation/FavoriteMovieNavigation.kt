package com.example.favorite.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.example.favorite.presentation.FavoriteMovieScreen
import com.google.accompanist.navigation.animation.composable

const val favoriteMovieNavRoute = "favorite_movie_route"

fun NavController.navigateToFavoriteMovie(navOptions: NavOptions? = null) {
    this.navigate(favoriteMovieNavRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.favoriteMovieScreen(
    modifier: Modifier = Modifier,
    openDetails: (id: Int) -> Unit
) {
    composable(route = favoriteMovieNavRoute) {
        FavoriteMovieScreen(modifier, openDetails)
    }
}