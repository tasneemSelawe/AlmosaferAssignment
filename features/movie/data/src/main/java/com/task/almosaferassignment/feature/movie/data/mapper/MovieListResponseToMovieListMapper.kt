package com.task.almosaferassignment.feature.movie.data.mapper

import com.task.almosaferassignment.feature.movie.data.response.MovieResponse
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import javax.inject.Inject

class MovieListResponseToMovieListMapper @Inject constructor() {

    fun map(source: MovieResponse): Movie {
        return Movie(
            adult = source.adult?:false,
            id = source.id?:0,
            video = source.video?:false,
            voteAverage = source.voteAverage?:0.0,
            voteCount = source.voteCount?:0,
            overview = source.overview?:"",
            popularity = source.popularity?:0.0,
            posterPath = source.posterPath?:"",
            backdropPath = source.backdropPath?:"",
            title = source.title?:"",
            genreIds = source.genreIds?: listOf(),
            originalTitle = source.originalTitle?:"",
            originalLanguage = source.originalLanguage?:"",
            releaseDate = source.releaseDate?:""
        )
    }
}