package com.example.tmsxmlproject.networking.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: Application) {

    @Provides
    fun provideApplication(): Application = this.app

    @Provides
    fun provideContext(): Context = this.app
}