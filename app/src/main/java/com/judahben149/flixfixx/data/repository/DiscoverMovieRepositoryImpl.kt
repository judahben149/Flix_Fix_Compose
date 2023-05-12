package com.judahben149.flixfixx.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.judahben149.flixfixx.Constants
import com.judahben149.flixfixx.data.paging.DiscoverPagingSource
import com.judahben149.flixfixx.data.remote.MovieService
import com.judahben149.flixfixx.data.remote.model.DiscoverMoviesDataDTO
import com.judahben149.flixfixx.domain.model.MovieList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverMovieRepositoryImpl @Inject constructor(private val movieService: MovieService): DiscoverMovieRepository {

    override fun fetchDiscoverMovieList(): Flow<PagingData<MovieList>> {
        val pagingSourceFactory = { DiscoverPagingSource(movieService, Constants.INITIAL_PAGE_NUMBER) }

        Log.d("TAG", "Repository: Inside Repository fetch discover movies fxn")


        return Pager (
            config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
            pagingSourceFactory = pagingSourceFactory,
                ).flow
    }
}