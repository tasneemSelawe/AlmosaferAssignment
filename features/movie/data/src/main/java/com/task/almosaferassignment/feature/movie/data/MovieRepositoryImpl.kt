package com.task.almosaferassignment.feature.movie.data

import com.task.almosaferassignment.feature.movie.data.datasource.RemoteMovieDataSource
import com.task.almosaferassignment.feature.movie.data.mapper.MovieListResponseToMovieListMapper
import com.task.almosaferassignment.feature.movie.domain.MovieRepository
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource,
    private val movieResponseToMovieMapper: MovieListResponseToMovieListMapper
) : MovieRepository {
    override suspend fun getMovieList(sortBy: String): List<Movie> {
        return remoteDataSource.getMovieList(sortBy)
            .map { movieResponseToMovieMapper.map(it) }
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        return movieResponseToMovieMapper.map(remoteDataSource.getMovieDetails(movieId))


    }
}
