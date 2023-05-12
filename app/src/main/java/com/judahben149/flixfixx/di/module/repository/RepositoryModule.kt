package com.judahben149.flixfixx.di.module.repository

import com.judahben149.flixfixx.data.remote.MovieService
import com.judahben149.flixfixx.data.repository.DiscoverMovieRepository
import com.judahben149.flixfixx.data.repository.DiscoverMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDiscoverRepository(movieService: MovieService): DiscoverMovieRepository {
        return DiscoverMovieRepositoryImpl(movieService)
    }
}