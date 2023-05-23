package com.task.almosaferassignment.feature.movie.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.almosaferassignment.core.errorhandler.exception.RemoteException
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import com.task.almosaferassignment.feature.movie.domain.usecase.GetMovieListUseCase
import com.task.almosaferassignment.feature.movie.presentation.MovieType
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

    private val _loadingFlow = MutableStateFlow<Pair<Boolean,MovieType?>>(Pair(true,null))
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _errorFlow = MutableSharedFlow<RemoteException>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _popularMovieList = MutableStateFlow<List<Movie>?>(null)
    val popularMovieList = _popularMovieList.asStateFlow()

    private val _topRatedMovieList = MutableStateFlow<List<Movie>?>(null)
    val topRatedMovieList = _topRatedMovieList.asStateFlow()

    private val _revenueMovieList = MutableStateFlow<List<Movie>?>(null)
    val revenueMovieList = _revenueMovieList.asStateFlow()

    init {
        getMovieList(1,MovieType.Popularity)
        getMovieList(1,MovieType.TopRated)
        getMovieList(1,MovieType.Revenue)
    }

     fun getMovieList(page:Int,sortBy:MovieType) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingFlow.emit(Pair(true,sortBy))
            try {
                val result = getMovieListUseCase(sortBy.value,page)
                when(sortBy)
                {
                    MovieType.Popularity->{
                        val oldList = _popularMovieList.value?.toMutableList() ?: arrayListOf()
                        oldList.addAll(result)
                        _popularMovieList.emit(oldList)
                    }
                    MovieType.TopRated->{
                        val oldTopList = _topRatedMovieList.value?.toMutableList() ?: arrayListOf()
                        oldTopList.addAll(result)
                        _topRatedMovieList.emit(oldTopList)
                    }
                    MovieType.Revenue-> {
                        val oldRevenueList = _revenueMovieList.value?.toMutableList() ?: arrayListOf()
                        oldRevenueList.addAll(result)
                        _revenueMovieList.emit(oldRevenueList)
                    }
                }
//                val dif1 = async { getMovieListUseCase("popularity.desc",page) }
//                val dif2 = async { getMovieListUseCase("vote_average.desc",page) }
//                val dif3 = async { getMovieListUseCase("revenue.desc",page) }
//
//                _popularMovieList.emit(dif1.await())
//                _topRatedMovieList.emit(dif2.await())
//                _revenueMovieList.emit(dif3.await())

            } catch (exception: RemoteException) {
                _errorFlow.emit(exception)
            }
            _loadingFlow.emit(Pair(false, sortBy))
        }
    }

}