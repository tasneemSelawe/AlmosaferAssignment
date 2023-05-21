package com.task.almosaferassignment.feature.movie.data

import com.task.almosaferassignment.core.errorhandler.ErrorHandler
import com.task.almosaferassignment.core.errorhandler.result.getOrThrow
import com.task.almosaferassignment.core.errorhandler.result.map
import com.task.almosaferassignment.core.errorhandler.result.mapError
import com.task.almosaferassignment.core.errorhandler.result.runWithCatching
import com.task.almosaferassignment.errorhandler.di.Network
import com.task.almosaferassignment.feature.movie.data.datasource.RemoteMovieDataSource
import com.task.almosaferassignment.feature.movie.data.mapper.MovieListResponseToMovieListMapper
import com.task.almosaferassignment.feature.movie.domain.MovieRepository
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource,
    private val movieResponseToMovieMapper: MovieListResponseToMovieListMapper,
    @Network private val errorHandler: ErrorHandler
) : MovieRepository {
    override suspend fun getMovieList(sortBy: String): List<Movie> {
        return runWithCatching { remoteDataSource.getMovieList(sortBy) }
            .map { it.map(movieResponseToMovieMapper::map) }
            .mapError(errorHandler::getError)
            .getOrThrow()

    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        return runWithCatching { remoteDataSource.getMovieDetails(movieId) }
            .map(movieResponseToMovieMapper::map)
            .mapError(errorHandler::getError)
            .getOrThrow()


    }
}
