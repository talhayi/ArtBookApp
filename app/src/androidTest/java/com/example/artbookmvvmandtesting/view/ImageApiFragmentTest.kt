package com.example.artbookmvvmandtesting.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId

import com.example.artbookmvvmandtesting.R
import com.example.artbookmvvmandtesting.adapter.ImageRecyclerAdapter
import com.example.artbookmvvmandtesting.getOrAwaitValueAndroidTest
import com.example.artbookmvvmandtesting.launchFragmentInHiltContainer
import com.example.artbookmvvmandtesting.repo.FakeArtRepositoryTest
import com.example.artbookmvvmandtesting.viewmodel.ArtViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageApiFragmentTest {

    private lateinit var viewModel: ArtViewModel
    private lateinit var imageRecyclerAdapter: ImageRecyclerAdapter

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun selectImage(){

        val navController = Mockito.mock(NavController::class.java)
        val selectedImageUrl = "talhayildirim.com"
        val testViewModel = ArtViewModel(FakeArtRepositoryTest())

        launchFragmentInHiltContainer<ImageApiFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
            viewModel = testViewModel
            imageRecyclerAdapter.images = listOf(selectedImageUrl)
        }
        Espresso.onView(withId(R.id.imageRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageRecyclerAdapter.ImageViewHolder>(
                0,click()
            )
        )
        Mockito.verify(navController).popBackStack()
        assertThat(testViewModel.selectedImageUrl.getOrAwaitValueAndroidTest()).isEqualTo(selectedImageUrl)

    }
}












