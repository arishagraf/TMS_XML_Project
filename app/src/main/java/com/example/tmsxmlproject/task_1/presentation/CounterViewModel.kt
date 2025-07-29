package com.example.tmsxmlproject.task_1.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmsxmlproject.task_1.domain.GetCountUseCase

class CounterViewModel(
    private val getCountUseCase: GetCountUseCase
) : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    private val _shouldNavigateNext = MutableLiveData<Boolean>(false)
    val shouldNavigateNext: LiveData<Boolean> get() = _shouldNavigateNext

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    fun incrementCounter() {
        if (_counter.value >= 10){
            _msg.value = "Limit exceeded"
        } else {
            val value = getCountUseCase()
            _counter.value = value
        }
    }

    fun onGoToNextExampleClicked() {
        _shouldNavigateNext.value = true
    }
}