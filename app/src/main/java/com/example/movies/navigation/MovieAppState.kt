package com.example.movies.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.favorite.navigation.favoriteMovieNavRoute
import com.example.favorite.navigation.navigateToFavoriteMovie
import com.example.popular.navigation.navigateToPopularMovie
import com.example.popular.navigation.popularMovieNavRoute
import com.example.profile.navigation.navigateToProfile
import com.example.profile.navigation.profileNavRoute
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberMovieAppState(
    navController: NavHostController = rememberAnimatedNavController()
): MovieAppState = remember(key1 = navController) {
    MovieAppState(navController)
}

@Stable
class MovieAppState(
    val navController: NavHostController
){
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when(currentDestination?.route) {
            popularMovieNavRoute -> TopLevelDestination.POPULAR
            favoriteMovieNavRoute -> TopLevelDestination.FAVORITE
            profileNavRoute -> TopLevelDestination.PROFILE
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    val shouldShowBottomBar: Boolean
        @Composable get() = when (currentDestination?.route) {
            popularMovieNavRoute -> true
            favoriteMovieNavRoute -> true
            profileNavRoute -> true
            else -> false
        }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            // Avoid multiple copies of the same destination when reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when(topLevelDestination) {
            TopLevelDestination.POPULAR -> navController.navigateToPopularMovie(topLevelNavOptions)
            TopLevelDestination.FAVORITE -> navController.navigateToFavoriteMovie(topLevelNavOptions)
            TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}