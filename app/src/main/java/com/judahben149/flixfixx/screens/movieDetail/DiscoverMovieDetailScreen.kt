package com.judahben149.flixfixx.screens.movieDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.judahben149.flixfixx.R
import com.judahben149.flixfixx.data.remote.ApiResponse
import com.judahben149.flixfixx.domain.model.Genre
import com.judahben149.flixfixx.domain.model.Movie
import com.judahben149.flixfixx.screens.common.CustomChip
import com.judahben149.flixfixx.ui.theme.White
import com.judahben149.flixfixx.utils.Constants
import com.judahben149.flixfixx.utils.Extensions.parseFriendlyDate
import com.judahben149.flixfixx.utils.Extensions.shortenVoteCount
import com.judahben149.flixfixx.utils.Extensions.toRunTimeString
import com.judahben149.flixfixx.utils.Extensions.trimDecimalPlaces
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
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
        modifier = Modifier
            .fillMaxSize()
    ) {

        movieDetails.value?.let { it ->
            if (it is ApiResponse.Success<Movie>) {

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {

                    MovieDetailsHeaderSection(movie = it.data)
                    MovieDetailsGenreSection(movie = it.data)
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
fun MovieDetailsHeaderSection(movie: Movie, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {

        //Backdrop
        CoilImage(
            modifier = modifier.matchParentSize(),
            imageModel = { Constants.IMAGE_BASE_URL.plus(movie.backdrop_path) },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                colorFilter = null
            ),
            component = rememberImageComponent {
                +CrossfadePlugin(
                    duration = 400
                )
//              +ShimmerPlugin(
//                  baseColor = Color.Transparent,
//                  highlightColor = Color.Cyan
//              )
            },
            previewPlaceholder = R.drawable.sample_backdrop
        )

        //Scrim
        Surface(
            color = Color.Transparent,
            modifier = Modifier.matchParentSize(),
            contentColor = Color.Transparent
        ) {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 300f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
            )
        }

        //Runtime
        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 10.dp),
            text = movie.runtime.toRunTimeString(),
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun MovieDetailsGenreSection(movie: Movie) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .horizontalScroll(rememberScrollState())
    ) {

        if (movie.adult) {
            CustomChip(
                chipText = "18+",
                hasChipIcon = false,
                chipIcon = painterResource(id = R.drawable.ic_cloud_done)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        CustomChip(
            chipText = movie.vote_average.trimDecimalPlaces(1).toString()
                .plus(" (${movie.vote_count.shortenVoteCount()})"),
            chipIcon = painterResource(id = R.drawable.ic_star),
            hasChipIcon = true
        )

        Spacer(modifier = Modifier.width(8.dp))

        movie.genres.forEach {
            CustomChip(
                chipText = it.name,
                hasChipIcon = false,
                chipIcon = painterResource(id = R.drawable.ic_cloud_done)
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun MovieDetailsTextSection(movie: Movie) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(6.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Text(
                text = movie.title,
                modifier = Modifier.padding(start = 3.dp),
                fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = movie.overview,
                modifier = Modifier.padding(start = 3.dp),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsHeaderSectionPreview() {
    MovieDetailsHeaderSection(movie = MovieDummy.movie)
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsGenreSectionPreview() {
    MovieDetailsGenreSection(movie = MovieDummy.movie)
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsTextSectionPreview() {
    MovieDetailsTextSection(movie = MovieDummy.movie)
}


@Preview(showBackground = true)
@Composable
fun DiscoverMovieDetailScreenContentPreview() {
    DiscoverMovieDetailScreenContent()
}


object MovieDummy {
    val movie = Movie(
        adult = true,
        backdrop_path = "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg",
        budget = 63000000.0,
        genres = listOf(Genre(18, "drama"), Genre(53, "Thriller"), Genre(35, "Comedy")),
        homepageUrl = "http://www.foxmovies.com/movies/fight-club",
        id = 550,
        imdb_id = "tt0137523",
        original_title = "Fight Club",
        original_language = "en",
        overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
        popularity = 79.264,
        poster_path = "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",
        release_date = "1999-10-15",
        revenue = 100853753.0,
        runtime = 139,
        status = "Released",
        tagline = "Mischief. Mayhem. Soap.",
        title = "Fight Club",
        video = false,
        vote_average = 8.433,
        vote_count = 26404
    )
}