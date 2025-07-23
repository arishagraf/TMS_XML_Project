package com.example.tmsxmlproject.task_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val allUsers = listOf(
        User("Alex"),
        User("Mary"),
        User("Ivan"),
        User("Olya"),
        User("Sergio"),
        User("Alice"),
        User("Max")
    )

    private val _viewState = MutableStateFlow(
        UserState(userViewState = UserViewState.Content(allUsers))
    )
    val viewState: StateFlow<UserState> = _viewState.asStateFlow()

    private val intentChannel = Channel<UserIntent>(Channel.UNLIMITED)
    private val userIntents = intentChannel.receiveAsFlow()

    init {
        processIntents()
    }

    fun sendIntent(intent: UserIntent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    private fun processIntents() {
        viewModelScope.launch {
            userIntents.collect { intent ->
                when (intent) {
                    is UserIntent.FilterUsers -> {
                        val filtered = allUsers.filter {
                            it.name.contains(intent.query, ignoreCase = true)
                        }
                        _viewState.update { state ->
                            state.copy(userViewState = UserViewState.Content(filtered))
                        }
                    }

                    is UserIntent.GoToNextScreen -> {
                        _viewState.update { state ->
                            state.copy(userViewState = UserViewState.NavigateNext(true))
                        }
                    }
                }
            }
        }
    }
}
