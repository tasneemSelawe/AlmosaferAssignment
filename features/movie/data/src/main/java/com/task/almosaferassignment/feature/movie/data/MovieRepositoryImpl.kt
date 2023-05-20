package com.task.almosaferassignment.feature.movie.data

import com.task.almosaferassignment.feature.movie.data.datasource.RemoteMovieDataSource
import com.task.almosaferassignment.feature.movie.domain.MovieRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource
) : MovieRepository {

}
