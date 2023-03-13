package com.example.detail.presentation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.pressBack
import com.example.detail.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@HiltAndroidTest
class MovieDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun pressBackBtn_popBackStack() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<MovieDetailFragment>{
            Navigation.setViewNavController(
                (this@launchFragmentInHiltContainer as MovieDetailFragment).requireView(), navController
            )
        }

        pressBack()

        verify(navController).popBackStack()
    }
}