package com.judahben149.flixfixx.domain.model

data class MovieList(
    val id: Int = 0,
    val isAdult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: List<Int> = emptyList(),
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.00,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val hasVideo: Boolean = false,
    val voteAverage: Double = 0.00,
    val voteCount: Int = 0
)
