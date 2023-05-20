package com.task.almosaferassignment.feature.movie.data.datasource

import com.task.almosaferassignment.feature.movie.data.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RemoteMovieDataSource {

    @GET("discover/movie")
    suspend fun getMovieList(@Query("sort_by") sort_by: String): List<MovieResponse>
}
