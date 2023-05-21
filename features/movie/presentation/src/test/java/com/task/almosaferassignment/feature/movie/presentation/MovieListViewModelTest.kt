package com.task.almosaferassignment.feature.movie.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.task.almosaferassignment.core.errorhandler.exception.RemoteException
import com.task.almosaferassignment.core.errorhandler.model.HttpErrorBodyModel
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import com.task.almosaferassignment.feature.movie.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
internal class MovieListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieListViewModel

    @Mock
    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieListViewModel(getMovieListUseCase)
    }

    @Test
    fun `test loading flow`() = runBlockingTest {
        val loadingFlowValue = true
        val loadingFlowMock = MutableStateFlow(loadingFlowValue)
        val viewModelSpy = Mockito.spy(viewModel)
        Mockito.doReturn(loadingFlowMock).`when`(viewModelSpy).loadingFlow

        val actualLoadingFlowValue = viewModelSpy.loadingFlow.first()

        assertEquals(loadingFlowValue, actualLoadingFlowValue)
    }


    @Test
    fun `test movie list loading`() = runTest {

        val mockMovieList = listOf(
            Movie(
                adult = true,
                backdropPath = "",
                genreIds = listOf(),
                id = 0,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = true,
                voteAverage = 0.0,
                voteCount = 0
            )
        )

        Mockito.`when`(getMovieListUseCase.invoke("popularity.desc")).thenReturn(mockMovieList)
        Mockito.`when`(getMovieListUseCase.invoke("vote_average.desc")).thenReturn(listOf())
        Mockito.`when`(getMovieListUseCase.invoke("revenue.desc")).thenReturn(null)

        viewModel.getMovieList()

        assertEquals(mockMovieList, viewModel.popularMovieList.firstOrNull())
        assertEquals(listOf<Movie>(), viewModel.topRatedMovieList.firstOrNull())
        assertEquals(null, viewModel.revenueMovieList.firstOrNull())
    }

    @Test
    fun `test error flow`() = runTest {
        val exception = Exception("Test Exception")
        Mockito.`when`(getMovieListUseCase.invoke("popularity.desc")).thenThrow(exception)

        viewModel.getMovieList()

        assertEquals(exception, viewModel.errorFlow.firstOrNull())

    }

}