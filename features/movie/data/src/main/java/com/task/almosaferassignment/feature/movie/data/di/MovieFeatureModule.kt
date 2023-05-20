package com.task.almosaferassignment.feature.movie.data.di

import com.task.almosaferassignment.feature.movie.data.MovieRepositoryImpl
import com.task.almosaferassignment.feature.movie.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MovieFeatureModule {

    @Binds
    fun bindMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository
}
