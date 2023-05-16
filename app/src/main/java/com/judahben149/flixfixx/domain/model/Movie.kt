package com.judahben149.flixfixx.domain.model

data class Movie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val budget: Double = 0.0,
    val genres: List<Genre> = emptyList(),
    val homepageUrl: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val revenue: Double = 0.0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)
