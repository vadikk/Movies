package com.example.popular.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.movie.model.Movie
import com.example.popular.R
import com.example.popular.testPopularModule
import com.example.testing.KoinTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PopularListFragmentTest {

    @get:Rule
    val koinTestRule = KoinTestRule(listOf(testPopularModule))


    @Test
    fun loadsTheDefaultResults() = runTest {
        var job: Job? = null
        val scenario = launchFragmentInContainer<PopularListFragment>()
        scenario.onFragment {
            job = launch {
                it.adapter?.submitData(
                    PagingData.from(
                        listOf(
                            Movie(id = 1)
                        )
                    )
                )
            }
            advanceUntilIdle()
        }

        onView(withId(R.id.moviesRV)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView

            assertThat(recyclerView.adapter?.itemCount).isEqualTo(1)
        }

        job?.cancel()
    }
}