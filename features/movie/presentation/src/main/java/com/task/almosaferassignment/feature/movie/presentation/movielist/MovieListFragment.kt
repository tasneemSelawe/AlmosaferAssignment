package com.task.almosaferassignment.feature.movie.presentation.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.task.almosaferassignment.core.errorhandler.exception.RemoteException
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import com.task.almosaferassignment.feature.movie.presentation.databinding.FragmentMovieListBinding
import com.task.almosaferassignment.navigation.di.getEntryPoint
import com.task.almosaferassignment.navigation.features.MovieDetailsEntry
import com.task.almosaferassignment.navigation.features.MovieDetailsEntry.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding get() = _binding!!

    private val viewModel by viewModels<MovieListViewModel>()

    private val popularAdapter = MovieListAdapter()
    private val topRatedAdapter = MovieListAdapter()
    private val revenueAdapter = MovieListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFlow()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewPopular.adapter = popularAdapter
        binding.recyclerViewTopRated.adapter = topRatedAdapter
        binding.recyclerViewRevenue.adapter = revenueAdapter

        popularAdapter.setOnMovieClickListener {
            getEntryPoint<MovieDetailsEntry> {
                navigate(
                    Arguments(
                        it.backdropPath,
                        it.title,
                        it.voteAverage,
                        it.overview
                    )
                )
            }
        }

        topRatedAdapter.setOnMovieClickListener {
            getEntryPoint<MovieDetailsEntry> {
                navigate(
                    Arguments(
                        it.backdropPath,
                        it.title,
                        it.voteAverage,
                        it.overview
                    )
                )
            }
        }

        revenueAdapter.setOnMovieClickListener {
            getEntryPoint<MovieDetailsEntry> {
                navigate(
                    Arguments(
                        it.backdropPath,
                        it.title,
                        it.voteAverage,
                        it.overview
                    )
                )
            }
        }
    }

    private fun initFlow() {
        lifecycleScope.launch {
            viewModel.loadingFlow.collect(::handelLoading)
        }
        lifecycleScope.launch {
            viewModel.errorFlow.collect(::handelError)
        }
        lifecycleScope.launch {
            viewModel.popularMovieList.collect(::handelPopularMovieList)
        }
        lifecycleScope.launch {
            viewModel.topRatedMovieList.collect(::handelTopRatedMovieList)
        }
        lifecycleScope.launch {
            viewModel.topRatedMovieList.collect(::handelRevenueMovieList)
        }
    }

    private fun handelLoading(show: Boolean) {
        binding.shimmerPopular.root.isVisible = show
        binding.shimmerTopRated.root.isVisible = show
        binding.shimmerRevenue.root.isVisible = show

    }

    private fun handelError(exception: RemoteException) {

    }

    private fun handelPopularMovieList(movieList: List<Movie>?) {
        if (movieList == null) {
            return
        }
        popularAdapter.submitList(movieList)
    }

    private fun handelTopRatedMovieList(movieList: List<Movie>?) {
        if (movieList == null) {
            return
        }
        topRatedAdapter.submitList(movieList)
    }

    private fun handelRevenueMovieList(movieList: List<Movie>?) {
        if (movieList == null) {
            return
        }
        revenueAdapter.submitList(movieList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}