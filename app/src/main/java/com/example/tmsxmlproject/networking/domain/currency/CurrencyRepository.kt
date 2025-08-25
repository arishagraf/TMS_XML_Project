package com.example.tmsxmlproject.networking.domain.currency

import com.example.tmsxmlproject.networking.data.currency.CurrencyModel

interface CurrencyRepository {

    suspend fun getRates(): List<CurrencyModel>
}