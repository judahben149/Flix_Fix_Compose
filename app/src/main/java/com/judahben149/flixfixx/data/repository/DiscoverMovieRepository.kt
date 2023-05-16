package com.judahben149.flixfixx.data.repository

import androidx.paging.PagingData
import com.judahben149.flixfixx.data.remote.ApiResponse
import com.judahben149.flixfixx.data.remote.model.DiscoverMoviesDataDTO
import com.judahben149.flixfixx.data.remote.model.MovieDetailDTO
import com.judahben149.flixfixx.domain.model.Movie
import com.judahben149.flixfixx.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface DiscoverMovieRepository {

    fun fetchDiscoverMovieList(): Flow<PagingData<MovieList>>

    suspend fun fetchMovieDetails(id: Int): Flow<ApiResponse<Movie>>
}