package com.judahben149.flixfixx.data.remote

import com.judahben149.flixfixx.data.remote.model.DiscoverResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun fetchDiscoverMovies(@Query("page") page: Int): Response<DiscoverResponseDTO>
}