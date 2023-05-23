package com.task.almosaferassignment.feature.movie.presentation

enum class MovieType (val value: String) {
    Popularity("popularity.desc"),
    TopRated("vote_average.desc"),
    Revenue("revenue.desc")
}