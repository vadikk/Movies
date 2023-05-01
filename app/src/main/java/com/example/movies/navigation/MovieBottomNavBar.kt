package com.example.movies.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.common.icon.Icon
import com.example.detail.navigation.detailMovieScreen
import com.example.detail.navigation.navigateToDetailMovie
import com.example.favorite.navigation.favoriteMovieScreen
import com.example.popular.navigation.popularMovieNavRoute
import com.example.popular.navigation.popularMovieScreen
import com.example.profile.navigation.profileScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost


@Composable
fun MovieBottomNavBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            BottomNavigationItem(
                selected = selected,
                icon = {
                    val icon = destination.selectedIcon

                    when (icon) {
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )

                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                },
                label = { Text(stringResource(id = destination.titleTextId)) },
                onClick = { onNavigateToDestination(destination) },
                selectedContentColor = MaterialTheme.colors.onBackground,
                unselectedContentColor = MaterialTheme.colors.background
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = popularMovieNavRoute,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        popularMovieScreen(
            onNavigateToDetails = { movieID -> navController.navigateToDetailMovie("$movieID") }
        )
        favoriteMovieScreen(modifier){ navController.navigateToDetailMovie("$it") }
        profileScreen(modifier)
        detailMovieScreen(onBackClick)
    }
}


private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false