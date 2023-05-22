package com.task.almosaferassignment.feature.movie.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import com.task.almosaferassignment.feature.movie.domain.usecase.GetMovieListUseCase
import com.task.almosaferassignment.feature.movie.presentation.movielist.MovieListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest2 {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getMovieListUseCase: GetMovieListUseCase = mockk(relaxed = true)

    private val viewModel: MovieListViewModel =
        MovieListViewModel(getMovieListUseCase = getMovieListUseCase)

    @Test
    fun `test that loading flow value is true when getMoviesList is called`() = runTest {
        //given
        coEvery { getMovieListUseCase(any()) } returns listOf(
            getMockedMovie(),
            getMockedMovie(),
            getMockedMovie()
        )

        //when
        viewModel.getMovieList()

        //then
        Assert.assertEquals(true, viewModel.loadingFlow.first())
    }


    @Test
    fun `test that list flows flow is empty when getMoviesList returns empty list`() =
        runTest {
            //given
            coEvery { getMovieListUseCase(any()) } returns listOf()

            //when
            viewModel.getMovieList()

            //then
            Assert.assertEquals(0, viewModel.popularMovieList.value?.size)
            Assert.assertEquals(0, viewModel.topRatedMovieList.value?.size)
            Assert.assertEquals(0, viewModel.revenueMovieList.value?.size)
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