package com.judahben149.flixfixx.navigation

sealed class Screen (val route: String) {
    object DiscoverMovie: Screen("discover_movie_screen")
    object MovieDetail: Screen("movie_detail_screen/{movieId}")
}
