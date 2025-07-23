package com.example.tmsxmlproject.task_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmsxmlproject.task_1.singleLiveEvent.SingleLiveEvent

class CounterViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    private val _shouldNavigateNext= MutableLiveData<Boolean>(false)
    val shouldNavigateNext: LiveData<Boolean> get() = _shouldNavigateNext

    fun incrementCounter() {
        _counter.value = (_counter.value ?: 0) + 1
    }

    fun onGoToNextExampleClicked() {
        _shouldNavigateNext.value = true
    }
}