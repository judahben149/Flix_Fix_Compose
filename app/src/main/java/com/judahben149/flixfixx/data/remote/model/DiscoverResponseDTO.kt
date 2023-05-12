package com.judahben149.flixfixx.data.remote.model

import com.google.gson.annotations.SerializedName

data class DiscoverResponseDTO(
    val page: Int,
    @SerializedName("results")
    val `data`: List<DiscoverMoviesDataDTO>,
    val total_pages: Int,
    val total_results: Int
) {
    val isLastPage = page == total_pages
}



data class DiscoverMoviesDataDTO(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
