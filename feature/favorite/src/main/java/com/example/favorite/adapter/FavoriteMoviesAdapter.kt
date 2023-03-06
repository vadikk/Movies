package com.example.favorite.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movie.model.Movie

internal class FavoriteMoviesAdapter(
    private val clickItem: (id: Int) -> Unit,
    private val unSelectItem: (id: Int) -> Unit
): ListAdapter<Movie, MovieVH>(PopularDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH =
        MovieVH.create(parent)

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val item = getItem(holder.bindingAdapterPosition)
        val id = item?.id ?: 0

        holder.bind(
            item,
            { clickItem(id) },
            { unSelectItem(id) }
        )
    }

    override fun onViewRecycled(holder: MovieVH) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    class PopularDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Movie, newItem: Movie): Any {
            return true
        }
    }
}