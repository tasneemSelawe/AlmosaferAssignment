package com.task.almosaferassignment.feature.movie.data.di

import com.task.almosaferassignment.feature.movie.data.datasource.RemoteMovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteMovieDataSourceModule {

    @Provides
    fun provideRemoteMovieDataSource(retrofit: Retrofit): RemoteMovieDataSource {
        return retrofit.create()
    }
}
