package com.task.almosaferassignment.feature.movie.domain.usecase

import com.task.almosaferassignment.feature.movie.domain.MovieRepository
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(sortBy: String,page:Int): List<Movie> {
        return movieRepository.getMovieList(sortBy,page)
    }
}