package com.example.tmsxmlproject.networking.di

import com.example.tmsxmlproject.networking.domain.Engine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CarModule {

    @Provides
    fun provideEngine(): Engine {
        return Engine()
    }
}