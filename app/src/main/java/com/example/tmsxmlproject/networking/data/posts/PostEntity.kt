package com.example.tmsxmlproject.networking.data.posts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val userId: Int,
    val title: String,
    val body: String
)