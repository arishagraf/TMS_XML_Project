package com.example.tmsxmlproject.domain

class GetItemListUseCase(
    private val repository: Repository,
) {

    operator fun invoke(): List<ItemModel> {
        val items = repository.getItems()
        val sortedList = sort(items)
        return sortedList
    }

    private fun sort(items: List<ItemModel>) = items.toSet().toList()
}