package com.example.tmsxmlproject.domain

class RemoveItemUseCase(
    private val repository: Repository,
) {

    suspend operator fun invoke(position: Int) {
        repository.removeItem(position = position)
    }
}