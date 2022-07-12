package com.example.artbookmvvmandtesting.viewmodel

import com.example.artbookmvvmandtesting.repo.FakeArtRepository
import org.junit.Before

class ArtViewModelTest {

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){

        viewModel = ArtViewModel(FakeArtRepository())
    }
}