package com.judahben149.flixfixx.screens.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.judahben149.flixfixx.Constants
import com.judahben149.flixfixx.R
import com.judahben149.flixfixx.domain.model.MovieList
import com.judahben149.flixfixx.ui.theme.Typography
import com.judahben149.flixfixx.utils.Extensions.parseFriendlyDate

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListContent(modifier: Modifier, items: LazyPagingItems<MovieList>) {

    Log.d("TAG", "List Content: Inside List Content Screen")

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = items,
            key = { movieItem ->
                movieItem.id
            }
        ) { movieItem ->
            movieItem?.let { MovieListItem(movieListItem = movieItem) }
        }

        when (val state = items.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            else -> {}
        }

        when(val state = items.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            else -> {}
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieListItem(movieListItem: MovieList) {
    val context = LocalContext.current
    val imageUrl = Constants.IMAGE_BASE_URL + movieListItem.posterPath

    val image = rememberImagePainter(data = imageUrl) {
        crossfade(durationMillis = 250)
        error(R.drawable.ic_placeholder)
        placeholder(R.drawable.ic_placeholder)
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                painter = image,
                contentDescription = "movie poster",
                modifier = Modifier.size(60.dp, 80.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            ) {
                Text(
                    text = movieListItem.title,
                    style = Typography.titleLarge
                )

                Text(
                    text = movieListItem.releaseDate.parseFriendlyDate(),
                    style = Typography.bodySmall
                )
            }


        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun MovieListItemPreview() {
    MovieListItem(
        movieListItem = MovieList(
            id = 1,
            isAdult = true,
            backdropPath = "",
            genreIds = emptyList(),
            originalTitle = "",
            originalLanguage = "",
            overview = "",
            popularity = 0.00,
            posterPath = "",
            releaseDate = "April 20, 2023",
            title = "Guardians of the Galaxy 4",
            hasVideo = false,
            voteAverage = 0.00,
            voteCount = 0
        )
    )
}

//@Preview(showBackground = true)
//@Composable
//fun ListContentPreview() {
//    ListContent()
//}
