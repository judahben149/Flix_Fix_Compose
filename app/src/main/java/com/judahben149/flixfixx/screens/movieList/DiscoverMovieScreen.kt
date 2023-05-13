package com.judahben149.flixfixx.screens.movieList

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.judahben149.flixfixx.screens.common.ListContent

@OptIn(ExperimentalPagingApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DiscoverMovieScreen(
    navController: NavHostController,
    movieListViewModel: MovieListViewModel = hiltViewModel()
) {
    val fetchAllDiscoverMovies =
        movieListViewModel.fetchDiscoverMovieList.collectAsLazyPagingItems()

    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(flingAnimationSpec = rememberSplineBasedDecay<Float>(), state = rememberTopAppBarState())

    Scaffold(
        topBar = { MovieListTopAppBar(scrollBehaviour) },
        content = { padding ->
            ListContent(modifier = Modifier
                .fillMaxSize()
                .padding(padding), items = fetchAllDiscoverMovies)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DiscoverMovieScreenPreview() {
//    DiscoverMovieScreen()
}
