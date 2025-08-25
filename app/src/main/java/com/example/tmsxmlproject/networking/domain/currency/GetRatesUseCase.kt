package com.example.tmsxmlproject.networking.domain.currency

import com.example.tmsxmlproject.networking.data.currency.CurrencyModel
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) {

    suspend operator fun invoke(): List<CurrencyModel> = currencyRepository.getRates()
}