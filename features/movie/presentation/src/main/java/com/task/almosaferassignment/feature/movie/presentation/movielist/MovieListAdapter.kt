package com.task.almosaferassignment.feature.movie.presentation.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.task.almosaferassignment.feature.movie.domain.entity.Movie
import com.task.almosaferassignment.feature.movie.presentation.R
import com.task.almosaferassignment.feature.movie.presentation.databinding.ItemMovieBinding

class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.MatchViewHolder>(DIFF_CALLBACK) {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var recyclerView: RecyclerView
    private var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(ItemMovieBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnMovieClickListener(onMovieClickListener: ((Movie) -> Unit)) {
        this.onMovieClickListener = onMovieClickListener
    }


    inner class MatchViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.imageViewPoster.load(IMAGE_BASE_URL + item.posterPath) {
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_placeholder)
            }
        }
    }


    companion object {

        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

}