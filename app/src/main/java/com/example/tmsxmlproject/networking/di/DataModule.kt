package com.example.tmsxmlproject.networking.di

import com.example.tmsxmlproject.networking.data.PostRepositoryImpl
import com.example.tmsxmlproject.networking.domain.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

//    @Provides
//    //in non abstract class
//    fun providePostRepository(
//        apiService: ApiService
//    ): PostRepository {
//       return PostRepositoryImpl(apiService)
//    }
}