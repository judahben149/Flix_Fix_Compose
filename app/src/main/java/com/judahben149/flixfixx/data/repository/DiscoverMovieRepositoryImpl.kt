package com.judahben149.flixfixx.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.judahben149.flixfixx.data.paging.DiscoverPagingSource
import com.judahben149.flixfixx.data.remote.ApiResponse
import com.judahben149.flixfixx.data.remote.MovieService
import com.judahben149.flixfixx.data.remote.model.MovieDetailDTO
import com.judahben149.flixfixx.domain.mappers.network.NetworkMappersImpl
import com.judahben149.flixfixx.domain.model.Movie
import com.judahben149.flixfixx.domain.model.MovieList
import com.judahben149.flixfixx.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
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


    @WorkerThread
    override suspend fun fetchMovieDetails(id: Int): Flow<ApiResponse<Movie>> = flow {
        emit(ApiResponse.Loading)

        try {
            val movieDetails = movieService.fetchMovieDetail(id)
            movieDetails?.let { response ->
                if (response.isSuccessful) {
                    val movie = NetworkMappersImpl.movieDtoToMovieModel(response.body()!!)

                    emit(ApiResponse.Success(movie))
                } else emit(ApiResponse.Error(response.errorBody().toString()))
            }
        } catch (exception: HttpException) {
                emit(ApiResponse.Error(exception.message ?: "Something went wrong"))
        }
        catch (exception: HttpException) {
            emit(ApiResponse.Error(exception.message ?: "Please check your network connection"))
        }
        catch (exception: HttpException) {
            emit(ApiResponse.Error(exception.message ?: "Something went wrong"))
        }

    }
}