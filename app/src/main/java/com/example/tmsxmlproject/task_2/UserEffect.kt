package com.example.tmsxmlproject.task_2

sealed class UserEffect {
    data object NavigateNext : UserEffect()
}