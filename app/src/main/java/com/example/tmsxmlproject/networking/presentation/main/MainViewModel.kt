package com.example.tmsxmlproject.networking.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmsxmlproject.networking.domain.onboarding.CheckUserSawOnboardingUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val checkUserSawOnboardingUseCase: CheckUserSawOnboardingUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val stateFlow: StateFlow<Boolean> get() = _stateFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            val wasSeen = checkUserSawOnboardingUseCase.invoke()
            _stateFlow.emit(wasSeen)
        }
    }
}