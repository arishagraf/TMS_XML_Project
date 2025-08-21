package com.example.tmsxmlproject.networking.di

import com.example.tmsxmlproject.networking.data.ApiService
import com.example.tmsxmlproject.networking.data.NbrbApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggerIntercepter(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggerIntercepter: HttpLoggingInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggerIntercepter)
            .build()
        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder() //the baseUrl should end with /
            .baseUrl("https://6898e221ddf05523e5600f48.mockapi.io/teachMeSkills/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("nbrb")
    fun provideNbrbRetrofit(): Retrofit {
        return Retrofit.Builder() //the baseUrl should end with /
            .baseUrl("https://api.nbrb.by/exrates/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("nbrb")
    fun provideNbrbApiService(@Named("nbrb") retrofit: Retrofit): NbrbApiService {
        return retrofit.create(NbrbApiService::class.java)
    }
}