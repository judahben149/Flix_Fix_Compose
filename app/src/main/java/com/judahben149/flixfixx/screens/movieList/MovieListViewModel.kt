package com.judahben149.flixfixx.screens.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.judahben149.flixfixx.data.repository.DiscoverMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MovieListViewModel @Inject constructor(repository: DiscoverMovieRepository): ViewModel() {

    val fetchDiscoverMovieList = repository.fetchDiscoverMovieList().cachedIn(viewModelScope)
}