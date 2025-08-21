package com.example.tmsxmlproject.networking.data

import com.example.tmsxmlproject.networking.domain.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class CurrencyRepositoryImpl @Inject constructor(
    @Named("nbrb") private val nbrbApiService: NbrbApiService,
) : CurrencyRepository {

    override suspend fun getRates(): List<CurrencyModel> = withContext(Dispatchers.IO) {
        nbrbApiService.getRates(periodicity = 0)
    }
}