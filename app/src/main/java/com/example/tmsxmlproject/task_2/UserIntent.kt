package com.example.tmsxmlproject.task_2

sealed class UserIntent {
    data class FilterUsers(val query: String) : UserIntent()
    data object GoToNextScreen : UserIntent()
}