package com.task.almosaferassignment.core.navigation.di

import com.task.almosaferassignment.core.navigation.Feature
import com.task.almosaferassignment.core.navigation.features.MovieDetailsEntry
import com.task.almosaferassignment.core.navigation.features.MovieListEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @IntoMap
    @ClassKey(MovieListEntry::class)
    fun bindsMovieListEntry(movieListEntry: MovieListEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(MovieDetailsEntry::class)
    fun bindsMovieDetailsEntry(movieDetailsEntry: MovieDetailsEntry): Feature

}
