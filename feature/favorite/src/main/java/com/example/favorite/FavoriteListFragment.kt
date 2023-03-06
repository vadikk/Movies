package com.example.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.nav.NavManager
import com.example.common.nav.NavScreen
import com.example.favorite.adapter.FavoriteMoviesAdapter
import com.example.favorite.databinding.FragmentFavoriteListBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class FavoriteListFragment : Fragment() {

    private val favoriteListVM: FavoriteListVM by viewModel()
    private val navManager by inject<NavManager>()
    private var adapter: FavoriteMoviesAdapter? = null
    private var binding: FragmentFavoriteListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteMoviesAdapter(
            { navManager.navigate(NavScreen.MovieDetail(it.toString())) },
            { favoriteListVM.deleteFromFavorite(it) }
        )

        binding?.favoriteRV?.also {
            it.adapter = adapter
            it.layoutManager =
                LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                favoriteListVM.favoriteMovies.collect {
                    binding?.emptyListText?.isVisible = it.isEmpty()
                    adapter?.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}