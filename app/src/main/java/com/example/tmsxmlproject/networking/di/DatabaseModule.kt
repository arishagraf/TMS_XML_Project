package com.example.tmsxmlproject.networking.di

import android.content.Context
import androidx.room.Room
import com.example.tmsxmlproject.networking.data.AppDatabase
import com.example.tmsxmlproject.networking.data.posts.PostsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "APP_DATABASE"
        ).build()
    }

    @Provides
    fun providePostsDAO(appDatabase: AppDatabase): PostsDAO {
        return appDatabase.getPostsDAO()
    }
}