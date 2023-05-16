package com.judahben149.flixfixx.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.judahben149.flixfixx.data.remote.MovieService
import com.judahben149.flixfixx.domain.mappers.network.NetworkMappersImpl
import com.judahben149.flixfixx.domain.model.MovieList

class DiscoverPagingSource(
    private val movieService: MovieService,
    private val initialPage: Int
): PagingSource<Int, MovieList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieList> {
        val currentPage = params.key ?: initialPage
        Log.d("TAG", "load: Load fnx called")

        try {
            val response = movieService.fetchDiscoverMovies(currentPage)
            val endOfPaginationReached = response.body()?.page == response.body()?.total_pages
            val data = response.body()?.data?.map { dto ->
                NetworkMappersImpl.movieListDtoToMovieListModel(dto)
            }

            data?.let { movieList ->

                return LoadResult.Page<Int, MovieList>(
                    data = movieList,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } ?:
            return LoadResult.Page<Int, MovieList> (
                data = emptyList(),
                prevKey = null,
                nextKey = null
                    )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieList>): Int? {
        return state.anchorPosition
    }
}