package com.example.tmsxmlproject.networking.domain

import com.example.tmsxmlproject.networking.data.CurrencyModel
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) {

    suspend operator fun invoke(): List<CurrencyModel> = currencyRepository.getRates()
}