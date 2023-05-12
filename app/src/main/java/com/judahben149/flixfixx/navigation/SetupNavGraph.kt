package com.judahben149.flixfixx.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import com.judahben149.flixfixx.screens.movieList.DiscoverMovieScreen

@OptIn(ExperimentalPagingApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.DiscoverMovie.route) {
        Log.d("TAG", "SetupNavGraph: inside nav graph fxn")
        composable(route = Screen.DiscoverMovie.route) {
            DiscoverMovieScreen(navController = navController)
        }
    }
}