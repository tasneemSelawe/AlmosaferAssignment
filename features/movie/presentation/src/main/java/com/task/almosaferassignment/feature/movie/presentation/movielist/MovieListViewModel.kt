package com.task.almosaferassignment.feature.movie.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import com.task.almosaferassignment.feature.movie.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _loadingFlow = MutableStateFlow(true)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Exception>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _popularMovieList = MutableStateFlow<List<Movie>?>(null)
    val popularMovieList = _popularMovieList.asStateFlow()

    private val _topRatedMovieList = MutableStateFlow<List<Movie>?>(null)
    val topRatedMovieList = _topRatedMovieList.asStateFlow()

    private val _revenueMovieList = MutableStateFlow<List<Movie>?>(null)
    val revenueMovieList = _revenueMovieList.asStateFlow()

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingFlow.emit(true)
            try {
                val dif1 = async { getMovieListUseCase("popularity.desc") }
                val dif2 = async { getMovieListUseCase("vote_average.desc") }
                val dif3 = async { getMovieListUseCase("revenue.desc") }

                _popularMovieList.emit(dif1.await())
                _topRatedMovieList.emit(dif2.await())
                _revenueMovieList.emit(dif3.await())

            } catch (exception: Exception) {
                _errorFlow.emit(exception)
            }
            _loadingFlow.emit(false)
        }
    }

}