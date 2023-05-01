package com.example.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.example.profile.presentation.ProfileScreen
import com.google.accompanist.navigation.animation.composable

const val profileNavRoute = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(profileNavRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileScreen(modifier: Modifier = Modifier) {
    composable(route = profileNavRoute) {
        ProfileScreen(modifier)
    }
}