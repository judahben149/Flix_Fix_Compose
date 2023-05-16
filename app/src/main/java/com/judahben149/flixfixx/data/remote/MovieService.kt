package com.judahben149.flixfixx.data.remote

import com.judahben149.flixfixx.data.remote.model.DiscoverResponseDTO
import com.judahben149.flixfixx.data.remote.model.MovieDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun fetchDiscoverMovies(@Query("page") page: Int): Response<DiscoverResponseDTO>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") id: Int): Response<MovieDetailDTO>
}