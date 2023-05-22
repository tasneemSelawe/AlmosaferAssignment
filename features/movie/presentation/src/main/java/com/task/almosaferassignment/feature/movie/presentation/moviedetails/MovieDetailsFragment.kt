package com.task.almosaferassignment.feature.movie.presentation.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.task.almosaferassignment.feature.movie.presentation.R
import com.task.almosaferassignment.feature.movie.presentation.databinding.FragmentMovieDetailsBinding
import com.task.almosaferassignment.navigation.features.MovieDetailsEntry
import com.task.almosaferassignment.navigation.ktx.navigationArguments

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding get() = _binding!!

    private val argument by navigationArguments<MovieDetailsEntry.Arguments>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieData()
    }

    private fun initMovieData() {
        binding.textViewMovieName.text = argument.name
        binding.textViewMovieOverview.text = argument.overView
        binding.textViewMovieRating.text = String.format(getString(R.string.rating), argument.rate)
        binding.imageViewPoster.load(IMAGE_BASE_URL + argument.image) {
            placeholder(R.drawable.ic_image_placeholder)
            error(R.drawable.ic_image_placeholder)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"
    }

}