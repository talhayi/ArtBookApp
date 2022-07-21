package com.example.artbookmvvmandtesting.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.artbookmvvmandtesting.R
import com.example.artbookmvvmandtesting.getOrAwaitValueAndroidTest
import com.example.artbookmvvmandtesting.launchFragmentInHiltContainer
import com.example.artbookmvvmandtesting.repo.FakeArtRepositoryTest
import com.example.artbookmvvmandtesting.roomdb.Art
import com.example.artbookmvvmandtesting.viewmodel.ArtViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtDetailsFragmentTest {

    private lateinit var viewModel: ArtViewModel

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
    fun testNavigationFromArtDetailsToImageAPI(){

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }

        onView(withId(R.id.artImageView)).perform(click())
        verify(navController).navigate(
            ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
        )

    }
    @Test
    fun testOnBackPressed(){

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.pressBack()
        verify(navController).popBackStack()
    }

    @Test
    fun testSave(){

        val testViewModel = ArtViewModel(FakeArtRepositoryTest())
        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory

        ){
            viewModel = testViewModel
        }
        onView(withId(R.id.nameText)).perform(replaceText("Mona Lisa"))
        onView(withId(R.id.artistText)).perform(replaceText("Da Vinci"))
        onView(withId(R.id.yearText)).perform(replaceText("1500"))
        onView(withId(R.id.saveButton)).perform(click())
        assertThat(testViewModel.artList.getOrAwaitValueAndroidTest()).contains(
            Art("Mona Lisa","Da Vinci",1500,"")
        )
    }
}