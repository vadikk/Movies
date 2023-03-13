package com.example.movies

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.common.nav.NavManager
import com.example.common.nav.NavScreen
import com.example.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navManager: NavManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavManager()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.isVisible =
                destination.id != com.example.detail.R.id.movieDetailFragment
        }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            when (it) {
                is NavScreen.MovieDetail -> {
                    findNavController(R.id.navHostFragment).navigateToScreen(it.route)
                }
                else -> Unit
            }
        }
    }

    private fun NavController.navigateToScreen(route: String) {
        val deeplink =
            NavDeepLinkRequest.Builder.fromUri(
                Uri.parse(
                    getString(com.example.detail.R.string.detail_deeplink).replace(
                        "{movieID}", route
                    )
                )
            )
                .build()
        navigate(
            deeplink, NavOptions.Builder()
                .setEnterAnim(R.anim.fragment_slide_left_enter)
                .setExitAnim(R.anim.fragment_slide_left_exit)
                .setPopEnterAnim(R.anim.fragment_slide_right_enter)
                .setPopExitAnim(R.anim.fragment_slide_right_exit).build()
        )
    }
}