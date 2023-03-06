package com.example.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.dispose
import coil.load
import com.example.favorite.databinding.ItemMovieBinding
import com.example.movie.TMDB_IMAGE
import com.example.movie.model.Movie

internal class MovieVH(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie?, click: () -> Unit, unSelect: () -> Unit) {
        if (movie == null) return

        with(binding) {
            movieImageID.load(TMDB_IMAGE + movie.posterPath)
            movieTitleID.text = movie.title
            movieText.text = movie.overview
            movieRelease.text = movie.releaseDate
            selectMovie.isActivated = movie.isSelected

            selectMovie.setOnClickListener { unSelect() }
            movieCard.setOnClickListener { click() }
        }
    }

    fun unbind() {
        binding.movieImageID.dispose()
    }

    companion object {
        fun create(parent: ViewGroup): MovieVH {
            val binding =
                ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MovieVH(binding)
        }
    }
}