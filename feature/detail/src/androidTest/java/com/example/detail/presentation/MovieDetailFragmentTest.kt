package com.example.detail.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.pressBack
import com.example.detail.R
import com.example.detail.testMovieDetailModule
import com.example.testing.KoinTestRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class MovieDetailFragmentTest{

    @get:Rule
    val koinTestRule = KoinTestRule(listOf(testMovieDetailModule))

    @Test
    fun pressBackBtn_popBackStack(){
        val navController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<MovieDetailFragment>(themeResId = R.style.ThemeTest)
        scenario.onFragment{
            Navigation.setViewNavController(it.requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }
}