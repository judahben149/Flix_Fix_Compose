package com.judahben149.flixfixx.domain.mappers.network

import com.judahben149.flixfixx.data.remote.model.DiscoverMoviesDataDTO
import com.judahben149.flixfixx.data.remote.model.GenreX
import com.judahben149.flixfixx.data.remote.model.MovieDetailDTO
import com.judahben149.flixfixx.domain.model.Genre
import com.judahben149.flixfixx.domain.model.Movie
import com.judahben149.flixfixx.domain.model.MovieList

object NetworkMappersImpl: NetworkMappers {

    override fun movieListDtoToMovieListModel(dto: DiscoverMoviesDataDTO): MovieList {
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

    override fun movieDtoToMovieModel(dto: MovieDetailDTO): Movie {
        return Movie(
            adult = dto.adult,
            backdrop_path = dto.backdrop_path ?: "",
            budget = dto.budget.toDouble(),
            genres = dto.genres.map { genreDtoToGenreModel(it) },
            homepageUrl = dto.homepage ?: "",
            id = dto.id,
            imdb_id = dto.imdb_id ?: "",
            original_language = dto.original_language,
            original_title = dto.original_title,
            overview = dto.overview ?: "",
            popularity = dto.popularity,
            poster_path = dto.poster_path ?: "",
            release_date = dto.release_date,
            revenue = dto.revenue.toDouble(),
            runtime = dto.runtime ?: 0,
            status = dto.status,
            tagline = dto.tagline ?: "",
            title = dto.title,
            video = dto.video,
            vote_average = dto.vote_average,
            vote_count = dto.vote_count
        )
    }

    override fun genreDtoToGenreModel(dto: GenreX): Genre {
        return Genre(
            id = dto.id,
            name = dto.name
        )
    }


}