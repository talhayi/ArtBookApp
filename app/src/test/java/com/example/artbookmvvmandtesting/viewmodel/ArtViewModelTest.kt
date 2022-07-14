package com.example.artbookmvvmandtesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artbookmvvmandtesting.MainCoroutineRule
import com.example.artbookmvvmandtesting.getOrAwaitValueTest
import com.example.artbookmvvmandtesting.repo.FakeArtRepository
import com.example.artbookmvvmandtesting.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.net.ssl.SSLEngineResult

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    //works mainthread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){

        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`(){

        viewModel.makeArt("Salvator Mundi","Da Vinci","")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert art without name returns error`(){

        viewModel.makeArt("","Da Vinci","1500")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName returns error`(){

        viewModel.makeArt("Salvator Mundi","","1500")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}