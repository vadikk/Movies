package com.example.popular.presentation.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

internal class LoadStatePagingAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<ErrorStateVH>() {

    override fun onBindViewHolder(holder: ErrorStateVH, loadState: LoadState) {
        holder.bind(retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ErrorStateVH =
        ErrorStateVH.create(parent)
}