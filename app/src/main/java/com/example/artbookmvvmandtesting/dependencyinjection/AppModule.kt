package com.example.artbookmvvmandtesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.artbookmvvmandtesting.api.RetrofitApi
import com.example.artbookmvvmandtesting.roomdb.ArtDatabase
import com.example.artbookmvvmandtesting.util.Util.BASE_URL
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ArtDatabase::class.java,"ArtBookDB").build()

    @Singleton
    @Provides
    fun injectDao(
        database: ArtDatabase
    ) = database.artDao()


    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }
}