package com.judahben149.flixfixx.screens.movieDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.judahben149.flixfixx.data.remote.ApiResponse
import com.judahben149.flixfixx.domain.model.Genre
import com.judahben149.flixfixx.domain.model.Movie
import com.judahben149.flixfixx.utils.Constants
import com.judahben149.flixfixx.utils.Extensions.parseFriendlyDate
import com.judahben149.flixfixx.utils.Extensions.toRunTimeString
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun DiscoverMovieDetailScreen(
    navController: NavHostController,
    movieId: Int
) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val movieDetails = movieDetailViewModel.movieDetail

    LaunchedEffect(key1 = true) {
        movieDetailViewModel.fetchMovieDetail(movieId)
    }

    Column(
        modifier = Modifier.fillMaxSize()

    ) {

        movieDetails.value?.let { it ->
            if (it is ApiResponse.Success<Movie>) {

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    ) {

                        //Backdrop
                        CoilImage(
                            modifier = Modifier.matchParentSize(),
                            imageModel = { Constants.IMAGE_BASE_URL.plus(it.data.backdrop_path) },
                            imageOptions = ImageOptions(
                                alignment = Alignment.Center,
                                colorFilter = null
                            ),
                            component = rememberImageComponent {
                                +CircularRevealPlugin(
                                    duration = 400
                                )
                            }
                        )
                    }

                    MovieDetailsTextSection(it.data)
                }
            }
        }
    }
}

@Composable
fun DiscoverMovieDetailScreenContent() {

}

@Composable
fun MovieDetailsTextSection(movie: Movie) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Text(
                text = movie.overview,
                modifier = Modifier.padding(start = 3.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Runtime: ".plus(movie.runtime.toRunTimeString()),
                modifier = Modifier.padding(start = 3.dp)
            )
            Text(
                text = "Released: ".plus(movie.release_date.parseFriendlyDate()),
                modifier = Modifier.padding(start = 3.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsTextSectionPreview() {
    MovieDetailsTextSection(
        movie = Movie().copy(
            runtime = 139,
            release_date = "1999-10-15",
            overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
            genres = listOf(Genre(18, "drama"), Genre(53, "Thriller"), Genre(35, "Comedy"))
        )
    )
}


@Preview(showBackground = true)
@Composable
fun DiscoverMovieDetailScreenContentPreview() {
    DiscoverMovieDetailScreenContent()
}

