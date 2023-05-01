package com.example.favorite.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.component.ItemMovie
import com.example.common_ui_res.R
import com.example.movie.TMDB_IMAGE


@Composable
internal fun FavoriteMovieScreen(
    modifier: Modifier = Modifier,
    openDetails: (id: Int) -> Unit
) {
    val favoriteListVM: FavoriteListVM = hiltViewModel()
    val movies by favoriteListVM.favoriteMovies.collectAsStateWithLifecycle()

    Surface(color = MaterialTheme.colors.primary) {
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier.fillMaxSize()
        ) {
            items(
                items = movies,
                key = { it.id }
            ) { movie ->
                ItemMovie(
                    id = movie.id,
                    title = movie.title,
                    overview = movie.overview,
                    releaseDate = movie.releaseDate,
                    posterPathUrl = TMDB_IMAGE + movie.posterPath,
                    selectIcon = R.drawable.ic_select,
                    selectOrUnselect = { favoriteListVM.deleteFromFavorite(movie.id) },
                    openDetails = openDetails
                )
            }
        }
    }
}