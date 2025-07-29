package com.example.tmsxmlproject.task_1.data

import com.example.tmsxmlproject.task_1.domain.CounterRepository

class CounterRepositoryImpl: CounterRepository {

    private var counter = 0

    override fun getCurrentCount(): Int {
       return counter
    }

    override fun setCurrentCount(newCountValue: Int) {
      counter = newCountValue
    }
}