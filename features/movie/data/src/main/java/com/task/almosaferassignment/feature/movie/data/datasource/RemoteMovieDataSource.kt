package com.task.almosaferassignment.feature.movie.data.datasource

import com.serjltt.moshi.adapters.Wrapped
import com.task.almosaferassignment.feature.movie.data.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface RemoteMovieDataSource {

    @GET("discover/movie")
    @Wrapped(path = ["results"])
    suspend fun getMovieList(
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int
    ): List<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieResponse
}
