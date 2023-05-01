package com.example.movies.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import com.example.common.icon.Icon
import com.example.movies.R

enum class TopLevelDestination (
    val selectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
){
    POPULAR(
        selectedIcon = Icon.ImageVectorIcon(Icons.Default.Home),
        iconTextId = R.string.popular,
        titleTextId = R.string.popular
    ),
    FAVORITE(
        selectedIcon = Icon.ImageVectorIcon(Icons.Default.Favorite),
        iconTextId = R.string.favorites,
        titleTextId = R.string.favorites
    ),
    PROFILE(
        selectedIcon = Icon.ImageVectorIcon(Icons.Default.Person),
        iconTextId = R.string.profile,
        titleTextId = R.string.profile
    )
}