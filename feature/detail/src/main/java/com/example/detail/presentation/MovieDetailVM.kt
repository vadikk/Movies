package com.example.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.model.MovieDetail
import com.example.movie.repository.movie.MovieRepository
import com.example.movie.repository.movieDetail.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieDetailVM @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val moveID =
        if (savedStateHandle.contains("movieID")) savedStateHandle.get<String>("movieID")?.toInt() ?: 0
        else 0

    private val _uiState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val uiState: StateFlow<MovieDetailState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val favoriteMovie = movieDetailRepository.getFavoriteMovie(moveID)

            delay(400)
            val result = try {
                movieDetailRepository.getDetails(moveID)
            }catch (e: Exception){
                e.printStackTrace()
                Result.failure(e)
            }

            if(result.isSuccess)
                _uiState.update {
                    MovieDetailState.MovieDetailData(
                        result.getOrNull()?.copy(isSelected = favoriteMovie != null)
                    )
                }
        }
    }
}

internal sealed interface MovieDetailState {
    data class MovieDetailData(val data: MovieDetail?): MovieDetailState
    object Loading: MovieDetailState
}