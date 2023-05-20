package com.task.almosaferassignment.feature.movie.data.mapper

import com.task.almosaferassignment.feature.movie.data.response.MovieResponse
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import javax.inject.Inject

class MovieListResponseToMovieListMapper @Inject constructor() {

    fun map(source: MovieResponse): Movie {
        return Movie(
            adult = source.adult,
            id = source.id,
            video = source.video,
            voteAverage = source.voteAverage,
            voteCount = source.voteCount,
            overview = source.overview,
            popularity = source.popularity,
            posterPath = source.posterPath,
            backdropPath = source.backdropPath,
            title = source.title,
            genreIds = source.genreIds,
            originalTitle = source.originalTitle,
            originalLanguage = source.originalLanguage,
            releaseDate = source.releaseDate
        )
    }
}