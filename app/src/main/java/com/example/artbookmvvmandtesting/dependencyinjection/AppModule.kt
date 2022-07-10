package com.example.artbookmvvmandtesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.artbookmvvmandtesting.R
import com.example.artbookmvvmandtesting.api.RetrofitApi
import com.example.artbookmvvmandtesting.repo.ArtRepository
import com.example.artbookmvvmandtesting.repo.ArtRepositoryInterface
import com.example.artbookmvvmandtesting.roomdb.ArtDao
import com.example.artbookmvvmandtesting.roomdb.ArtDatabase
import com.example.artbookmvvmandtesting.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )

    @Singleton
    @Provides
    fun injectNormalRepo(dao: ArtDao,api: RetrofitApi)=ArtRepository(dao,api) as ArtRepositoryInterface
}