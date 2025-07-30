package com.example.tmsxmlproject.domain

class GetItemListUseCase(
    private val repository: Repository,
) {

    operator fun invoke(): List<ItemModel> {
        return repository.getItems()
    }
}