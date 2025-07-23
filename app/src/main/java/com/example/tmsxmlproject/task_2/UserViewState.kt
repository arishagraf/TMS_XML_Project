package com.example.tmsxmlproject.task_2

data class UserState(
    val sideEffectMessage: String = "Hi, I am always here despite of current state",
    val userViewState: UserViewState
)

sealed class UserViewState {
    data class Content(val users: List<User> = emptyList()) : UserViewState()
    data class NavigateNext(val shouldNavigate: Boolean) : UserViewState()
}