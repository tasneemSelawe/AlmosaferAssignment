package com.task.almosaferassignment.feature.movie.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.almosaferassignment.core.errorhandler.exception.RemoteException
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import com.task.almosaferassignment.feature.movie.domain.usecase.GetMovieListUseCase
import com.task.almosaferassignment.feature.movie.presentation.movielist.MovieListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getMovieListUseCase: GetMovieListUseCase = mockk(relaxed = true)

    private val viewModel: MovieListViewModel =
        MovieListViewModel(getMovieListUseCase = getMovieListUseCase)

    @Test
    fun `test that loading flow value is true when getPopularMoviesList is called`() = runTest {
        //given
        coEvery { getMovieListUseCase(any(),any()) } returns listOf(
            getMockedMovie()
        )

        //when
        viewModel.getMovieList(1,MovieType.Popularity)

        //then
        Assert.assertEquals(Pair(true,MovieType.Popularity), viewModel.loadingFlow.first())
    }


    @Test
    fun `test that list flows flow is empty when getPopularMoviesList returns empty list`() =
        runTest {
            //given
            coEvery { getMovieListUseCase(any(),any()) } returns listOf()

            //when
            viewModel.getMovieList(1,MovieType.Popularity)

            //then
            Assert.assertEquals(0, viewModel.popularMovieList.value?.size)
        }


    @Test
    fun `test that error flow value is set when getMoviesList fails`() = runTest {
        val mockedException = mockk<RemoteException>(relaxed = true)
        //given
        coEvery { getMovieListUseCase(any(),any()) }.throws(mockedException)
        // when
        viewModel.getMovieList(1,MovieType.Popularity)

        //then
        Assert.assertEquals(mockedException, viewModel.errorFlow.first())
    }



    private fun getMockedMovie() = Movie(
        adult = false,
        backdropPath = "",
        genreIds = listOf(),
        id = 1,
        originalLanguage = "",
        originalTitle = "",
        overview = "",
        popularity = 1.0,
        posterPath = "",
        releaseDate = "",
        title = "",
        video = false,
        voteAverage = 1.0,
        voteCount = 1
    )
}