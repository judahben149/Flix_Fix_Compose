package com.judahben149.flixfixx.screens.movieDetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judahben149.flixfixx.data.remote.ApiResponse
import com.judahben149.flixfixx.data.repository.DiscoverMovieRepository
import com.judahben149.flixfixx.domain.mappers.network.NetworkMappersImpl
import com.judahben149.flixfixx.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: DiscoverMovieRepository) :
    ViewModel() {

//    private val _detailState: MutableSharedFlow<MovieDetailState> = MutableSharedFlow()
//    val detailState: SharedFlow<MovieDetailState> = _detailState.asSharedFlow()

    val movieDetail: MutableState<ApiResponse<Movie>?> = mutableStateOf(null)

    fun fetchMovieDetail(id: Int) {
        viewModelScope.launch {
            repository.fetchMovieDetails(id).onEach { movie ->
                if (movie is ApiResponse.Success<Movie>) {
                    movieDetail.value = movie
                }
//                else {}
            }.launchIn(viewModelScope)
        }
    }
}