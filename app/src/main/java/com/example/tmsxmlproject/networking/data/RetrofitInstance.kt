package com.example.tmsxmlproject.networking.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        val loggerIntercepter = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggerIntercepter)
            .build()

        Retrofit.Builder() //the baseUrl should end with /
            .baseUrl("https://6898e221ddf05523e5600f48.mockapi.io/teachMeSkills/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}