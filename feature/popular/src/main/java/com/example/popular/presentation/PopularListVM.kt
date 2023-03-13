package com.example.popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.common.di.IoDispatcher
import com.example.movie.model.Movie
import com.example.movie.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PopularListVM @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val pagingMovieData = movieRepository.getPopularMovies()
        .cachedIn(viewModelScope)
        .combine(movieRepository.favoriteMovies()) { pagingData: PagingData<Movie>, favoriteList: List<Movie> ->
            pagingData.map { pagignMovie ->
                val favoriteMovie = favoriteList.filter { it.id == pagignMovie.id }.firstOrNull()
                pagignMovie.copy(
                    isSelected = favoriteMovie != null
                )
            }
        }.flowOn(dispatcherIO)

    fun setFavoriteMovie(movie: Movie?, isSelected: Boolean) {
        if (movie == null) return

        viewModelScope.launch {
            if (isSelected) movieRepository.saveToFavorite(movie)
            else movieRepository.deleteFromFavorite(movie.id)
        }
    }

    init {
        viewModelScope.launch {
            movieRepository.favoriteMovies().collect {
                _favoriteMovies.value = it
            }
        }
    }
}

