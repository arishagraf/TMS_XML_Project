package com.example.tmsxmlproject.task_1.domain

class GetCountUseCase(
    private val counterRepository: CounterRepository,
) {

    operator fun invoke(): Int {
        val newValue = counterRepository.getCurrentCount() + 1
        counterRepository.setCurrentCount(newValue)
        return newValue
    }
}