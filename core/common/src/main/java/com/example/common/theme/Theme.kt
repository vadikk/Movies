package com.example.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightGray,
    background = White,
    onBackground = Black,
    onPrimary = LightGray,
    surface = ChipColor
)

private val LightColorPalette = lightColors(
    primary = LightGray,
    background = White,
    onBackground = Black,
    onPrimary = LightGray,
    surface = ChipColor
)

@Composable
fun MovieTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}