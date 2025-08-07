package com.example.tmsxmlproject.domain

interface Repository {

    suspend fun addItem(item: ItemModel)
    suspend fun removeItem(position: Int)
    suspend fun getItems(): List<ItemModel>
}