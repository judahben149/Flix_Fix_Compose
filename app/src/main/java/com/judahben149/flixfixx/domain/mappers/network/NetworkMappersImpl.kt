package com.judahben149.flixfixx.domain.mappers.network

import com.judahben149.flixfixx.data.remote.model.DiscoverMoviesDataDTO
import com.judahben149.flixfixx.domain.model.MovieList

object NetworkMappersImpl: NetworkMappers {

    override fun movieDtoToMovieModel(dto: DiscoverMoviesDataDTO): MovieList {
        return MovieList(
            id = dto.id,
            isAdult = dto.adult,
            backdropPath = dto.backdrop_path ?: "",
            genreIds = dto.genre_ids,
            originalLanguage = dto.original_language,
            originalTitle = dto.original_title,
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.poster_path ?: "",
            releaseDate = dto.release_date,
            title = dto.title,
            hasVideo = dto.video,
            voteAverage = dto.vote_average,
            voteCount = dto.vote_count
        )
    }
}