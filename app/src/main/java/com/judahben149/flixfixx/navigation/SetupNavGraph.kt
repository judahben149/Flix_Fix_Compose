package com.judahben149.flixfixx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import com.judahben149.flixfixx.screens.movieDetail.DiscoverMovieDetailScreen
import com.judahben149.flixfixx.screens.movieList.DiscoverMovieScreen

@OptIn(ExperimentalPagingApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.DiscoverMovie.route) {
        composable(route = Screen.DiscoverMovie.route) {
            DiscoverMovieScreen(navController = navController)
        }
        composable(route = Screen.MovieDetail.route) {
            val movieId = it.arguments?.getString("movieId")
            movieId?.let { id ->
                DiscoverMovieDetailScreen(navController = navController, id.toInt())
            }
        }
    }
}