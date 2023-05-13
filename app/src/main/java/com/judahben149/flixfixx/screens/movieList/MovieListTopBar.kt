package com.judahben149.flixfixx.screens.movieList

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListTopAppBar(scrollBehaviour: TopAppBarScrollBehavior) {

    TopAppBar(
        title = {
            Text(
                text = "Discover"
            )
        },
        scrollBehavior = scrollBehaviour
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MovieListTopAppBarPreview() {
    MovieListTopAppBar(TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState()))
}
