package com.example.tmsxmlproject.networking.data.currency

import com.example.tmsxmlproject.networking.domain.currency.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class CurrencyRepositoryImpl @Inject constructor(
    @Named("nbrb") private val nbrbApiService: NbrbApiService,
    @Named("audi") private val car: Car,
) : CurrencyRepository {

    override suspend fun getRates(): List<CurrencyModel> = withContext(Dispatchers.IO) {
        nbrbApiService.getRates(periodicity = 0)
    }
}

interface Car {
    fun fromGermany()
}

class Audi @Inject constructor() : Car {
    override fun fromGermany() {
        TODO("Not yet implemented")
    }
}

class BMW @Inject constructor() : Car {
    override fun fromGermany() {
        TODO("Not yet implemented")
    }
}