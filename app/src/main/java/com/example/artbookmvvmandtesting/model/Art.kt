package com.example.artbookmvvmandtesting.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    var name: String,
    var artistName: String,
    var year: Int,
    var imageUrl : String,
    @PrimaryKey(autoGenerate = true)
    var id: String? = null
)
