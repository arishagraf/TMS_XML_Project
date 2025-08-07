package com.example.tmsxmlproject.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(999)
    val stateflow: StateFlow<Int> get() = _stateFlow

    private val _sharedFlow = MutableSharedFlow<Int>(replay = 3)
    val sharedFlow: SharedFlow<Int> = _sharedFlow

    val flow = flow<Int> {
        for (i in 1..50) {
            emit(i)
            delay(1000)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.Main) {
            example()
            example2()
        }

        viewModelScope.launch {
            flow.collect {
                println("coldFlow2: $it")
            }
        }
        emitStateFlow()
    }

    private fun emitSharedFlow() {
        viewModelScope.launch {
            for (i in 1..50) {
                _sharedFlow.emit(i)
                delay(1000)
            }
        }
    }

    private fun emitStateFlow() { //todo
        viewModelScope.launch {
            for (i in 1..50) {
                _stateFlow.value = i
                delay(1000)
            }
        }
    }

    suspend fun example() {
        withContext(Dispatchers.IO) {
            // will executed io
        }
    }

    suspend fun example2() {
        withContext(Dispatchers.Default) {
            // will executed default
        }
    }

    suspend fun example3() {
        withContext(Dispatchers.Main) {

        }
    }
}