package com.example.tmsxmlproject.networking.domain

import com.example.tmsxmlproject.networking.data.CurrencyModel

interface CurrencyRepository {

    suspend fun getRates(): List<CurrencyModel>
}