package com.example.tmsxmlproject.task_1.domain

interface CounterRepository {
    fun getCurrentCount(): Int
    fun setCurrentCount(newCountValue: Int)
}