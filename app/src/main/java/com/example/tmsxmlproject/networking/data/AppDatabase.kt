package com.example.tmsxmlproject.networking.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmsxmlproject.networking.data.posts.PostEntity
import com.example.tmsxmlproject.networking.data.posts.PostsDAO

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostsDAO(): PostsDAO
}