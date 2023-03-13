package com.example.detail.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.detail.R
import com.example.detail.databinding.FragmentMovieDetailBinding
import com.example.detail.domain.setCustomText
import com.example.detail.domain.showSkeletonAnimation
import com.example.movie.TMDB_IMAGE
import com.example.movie.model.MovieDetail
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class MovieDetailFragment : Fragment() {

    private val movieDetailVM: MovieDetailVM by viewModels()
    private var binding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.back?.setOnClickListener { findNavController().popBackStack() }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieDetailVM.uiState.collect {
                    showUI(it)
                }
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun showUI(state: MovieDetailState) {
        when (state) {
            is MovieDetailState.Loading -> showSkeleton(true)
            is MovieDetailState.MovieDetailData -> {
                showSkeleton(false)
                fillUpData(state.data)
            }
        }
    }

    private fun fillUpData(movieDetail: MovieDetail?) {
        if (movieDetail == null) return

        binding?.movieImageDetail?.load(TMDB_IMAGE + movieDetail.posterPath)
        binding?.selectDetail?.isActivated = movieDetail.isSelected
        binding?.movieTitleDetail?.text = movieDetail.title
        binding?.overviewDet?.setCustomText(
            context?.resources?.getString(R.string.overview).orEmpty(),
            movieDetail.overview
        )
        movieDetail.genreApis.forEach {
            binding?.chipGroup?.addView(createTagChip(requireContext(), it.name))
        }
        binding?.movieReleaseDetail?.setCustomText(
            context?.resources?.getString(R.string.release_date).orEmpty(),
            movieDetail.releaseDate
        )
        binding?.movieBudgetDetail?.setCustomText(
            context?.resources?.getString(R.string.budget).orEmpty(),
            movieDetail.budget.toString() + "$"
        )
        binding?.movieRuntimeDetail?.setCustomText(
            context?.resources?.getString(R.string.runtime).orEmpty(),
            movieDetail.runtime.toString() + "m"
        )
        binding?.movieStatusDetail?.setCustomText(
            context?.resources?.getString(R.string.status).orEmpty(),
            movieDetail.status
        )
    }

    private fun createTagChip(context: Context, chipName: String): Chip {
        return Chip(context).apply {
            text = chipName
            setChipBackgroundColorResource(android.R.color.holo_blue_light)
            isCloseIconVisible = false
            setTextColor(ContextCompat.getColor(context, android.R.color.black))
            setTextAppearance(R.style.ChipTextAppearance)
        }

    }

    private fun showSkeleton(isShow: Boolean) {
        listOf(
            binding?.movieImageDetail,
            binding?.movieTitleDetail,
            binding?.overviewDet,
            binding?.genresTitle,
            binding?.movieReleaseDetail,
            binding?.movieBudgetDetail,
            binding?.movieRuntimeDetail,
            binding?.movieStatusDetail
        ).map { it.showSkeletonAnimation(R.color.skeleton_card_bg_color, isShow) }
    }
}