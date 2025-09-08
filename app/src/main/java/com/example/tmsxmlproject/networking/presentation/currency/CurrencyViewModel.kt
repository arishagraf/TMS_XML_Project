package com.example.tmsxmlproject.networking.presentation.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmsxmlproject.networking.data.currency.CurrencyModel
import com.example.tmsxmlproject.networking.domain.currency.GetRatesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<List<CurrencyModel>?> =
        MutableStateFlow<List<CurrencyModel>?>(null)
    val stateFlow: StateFlow<List<CurrencyModel>?> get() = _stateFlow.asStateFlow()

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
        }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            val result = getRatesUseCase.invoke()
            _stateFlow.emit(result)
        }
    }
}