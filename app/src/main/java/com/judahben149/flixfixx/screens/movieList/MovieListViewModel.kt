package com.judahben149.flixfixx.screens.movieList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.judahben149.flixfixx.data.remote.ApiResponse
import com.judahben149.flixfixx.data.repository.DiscoverMovieRepository
import com.judahben149.flixfixx.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: DiscoverMovieRepository): ViewModel() {

    val fetchDiscoverMovieList = repository.fetchDiscoverMovieList().cachedIn(viewModelScope)

}