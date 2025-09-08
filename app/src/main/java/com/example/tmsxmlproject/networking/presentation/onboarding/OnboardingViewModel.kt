package com.example.tmsxmlproject.networking.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmsxmlproject.networking.domain.onboarding.SetUserSawOnboardingUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val setUserSawOnboardingUseCase: SetUserSawOnboardingUseCase,
) : ViewModel(){

    private val _stateFlow: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val stateFlow: StateFlow<Boolean> get() = _stateFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun letsStartClicked() {
        viewModelScope.launch(coroutineExceptionHandler) {
            setUserSawOnboardingUseCase.invoke(true)
            _stateFlow.emit(true)
        }
    }
}