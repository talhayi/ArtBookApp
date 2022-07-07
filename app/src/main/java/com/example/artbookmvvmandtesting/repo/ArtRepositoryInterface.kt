package com.example.artbookmvvmandtesting.repo

import androidx.lifecycle.LiveData
import com.example.artbookmvvmandtesting.model.ImageResponse
import com.example.artbookmvvmandtesting.roomdb.Art
import com.example.artbookmvvmandtesting.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString: String): Resource<ImageResponse>
}