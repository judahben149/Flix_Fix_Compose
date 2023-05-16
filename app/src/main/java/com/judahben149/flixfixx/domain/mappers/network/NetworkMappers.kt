package com.judahben149.flixfixx.domain.mappers.network

import com.judahben149.flixfixx.data.remote.model.DiscoverMoviesDataDTO
import com.judahben149.flixfixx.data.remote.model.GenreX
import com.judahben149.flixfixx.data.remote.model.MovieDetailDTO
import com.judahben149.flixfixx.domain.model.Genre
import com.judahben149.flixfixx.domain.model.Movie
import com.judahben149.flixfixx.domain.model.MovieList

interface NetworkMappers {

    fun movieListDtoToMovieListModel(dto: DiscoverMoviesDataDTO): MovieList

    fun movieDtoToMovieModel(dto: MovieDetailDTO): Movie

    fun genreDtoToGenreModel(dto: GenreX): Genre
}