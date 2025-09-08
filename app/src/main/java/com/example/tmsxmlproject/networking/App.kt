package com.example.tmsxmlproject.networking

import android.app.Application
import com.example.tmsxmlproject.networking.di.AppComponent
import com.example.tmsxmlproject.networking.di.AppModule
import com.example.tmsxmlproject.networking.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    fun provideAppComponent(): AppComponent {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        return appComponent
    }
}