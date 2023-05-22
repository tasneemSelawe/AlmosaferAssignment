package com.task.almosaferassignment.feature.movie.domain

import com.task.almosaferassignment.feature.movie.domain.entity.Movie

interface MovieRepository {
    suspend fun getMovieList(sortBy: String,page:Int): List<Movie>

    suspend fun getMovieDetails(movieId: Int): Movie
}
