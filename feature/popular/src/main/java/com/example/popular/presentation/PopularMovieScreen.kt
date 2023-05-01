package com.example.popular.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.common.component.ItemMovie
import com.example.common_ui_res.R
import com.example.movie.TMDB_IMAGE
import com.example.movie.model.Movie

@Composable
internal fun PopularMovieScreen(
    onNavigateToDetails: (id: Int) -> Unit,
) {
    val popularListVM: PopularListVM = hiltViewModel()
    val movies = popularListVM.pagingMovieData.collectAsLazyPagingItems()

    PopularMovie(
        movies = movies,
        onNavigateToDetails = onNavigateToDetails,
        favoriteMovie = { movie, isSelected -> popularListVM.setFavoriteMovie(movie, isSelected) }
    )
}

@Composable
internal fun PopularMovie(
    movies: LazyPagingItems<Movie>,
    onNavigateToDetails: (id: Int) -> Unit,
    favoriteMovie: (movie: Movie?, isSelected: Boolean) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = movies.rememberLazyListState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.testTag("popularRV")
            ) {
                items(
                    items = movies,
                    key = { it.id }
                ) { movie ->
                    if (movie == null) return@items

                    var isSelectedMovie = movie.isSelected
                    val selectedIcon =
                        if (isSelectedMovie) R.drawable.ic_select else R.drawable.ic_unselect

                    ItemMovie(
                        id = movie.id,
                        title = movie.title,
                        overview = movie.overview,
                        releaseDate = movie.releaseDate,
                        posterPathUrl = TMDB_IMAGE + movie.posterPath,
                        selectIcon = selectedIcon,
                        selectOrUnselect = {
                            isSelectedMovie = !isSelectedMovie
                            favoriteMovie(movie, isSelectedMovie)
                        },
                        openDetails = onNavigateToDetails
                    )
                }

                item {
                    if (movies.loadState.append is LoadState.Error) {
                        RetryBtn {
                            movies.refresh()
                        }
                    }
                    if (movies.loadState.refresh is LoadState.Error) {
                        LaunchedEffect(key1 = snackbarHostState) {
                            snackbarHostState.showSnackbar(
                                message = (movies.loadState.refresh as LoadState.Error).error.message.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RetryBtn(
    retryCallback: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = { retryCallback() }) {
            Text(text = "Retry")
        }
    }
}

@Composable
private fun <T : Any> LazyPagingItems<T>.rememberLazyListState(): LazyListState {
    // After recreation, LazyPagingItems first return 0 items, then the cached items.
    // This behavior/issue is resetting the LazyListState scroll position.
    // Below is a workaround. More info: https://issuetracker.google.com/issues/177245496.
    return when (itemCount) {
        // Return a different LazyListState instance.
        0 -> remember(this) { LazyListState(0, 0) }
        // Return rememberLazyListState (normal case).
        else -> androidx.compose.foundation.lazy.rememberLazyListState()
    }
}

@Preview
@Composable
private fun RetryBtnPreview() {
    RetryBtn(
        retryCallback = {}
    )
}