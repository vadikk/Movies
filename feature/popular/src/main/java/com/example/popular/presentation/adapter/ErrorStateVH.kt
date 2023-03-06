package com.example.popular.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popular.databinding.ErrorStateLayoutBinding

internal class ErrorStateVH(private val binding: ErrorStateLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(click: () -> Unit) {
        with(binding) {
            retryBtn.setOnClickListener { click() }
        }
    }

    companion object {
        fun create(parent: ViewGroup): ErrorStateVH {
            val binding =
                ErrorStateLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ErrorStateVH(binding)
        }
    }
}