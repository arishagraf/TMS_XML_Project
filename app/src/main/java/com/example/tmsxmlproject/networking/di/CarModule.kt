package com.example.tmsxmlproject.networking.di

import com.example.tmsxmlproject.networking.domain.posts.Engine
import dagger.Module
import dagger.Provides

@Module
class CarModule {

    @Provides
    fun provideEngine(): Engine {
        return Engine()
    }
}