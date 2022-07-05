package com.example.artbookmvvmandtesting.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artbookmvvmandtesting.model.Art

@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase : RoomDatabase(){
    abstract fun artDao(): ArtDao
}