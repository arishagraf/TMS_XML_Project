package com.example.tmsxmlproject.domain

class AddItemUseCase(
    private val repository: Repository
) {

    operator fun invoke(text: String) {
        repository.addItem(item = ItemModel(text = text))
    }
}