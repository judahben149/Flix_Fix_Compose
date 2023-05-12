package com.judahben149.flixfixx.domain.mappers.network

import com.judahben149.flixfixx.data.remote.model.DiscoverMoviesDataDTO
import com.judahben149.flixfixx.domain.model.MovieList

interface NetworkMappers {

    fun movieDtoToMovieModel(dto: DiscoverMoviesDataDTO): MovieList
}