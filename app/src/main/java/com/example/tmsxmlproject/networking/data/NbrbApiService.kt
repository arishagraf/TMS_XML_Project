package com.example.tmsxmlproject.networking.data

import retrofit2.http.GET
import retrofit2.http.Query

interface NbrbApiService {

    @GET("rates")
    suspend fun getRates(@Query("periodicity") periodicity: Int): List<CurrencyModel>
}