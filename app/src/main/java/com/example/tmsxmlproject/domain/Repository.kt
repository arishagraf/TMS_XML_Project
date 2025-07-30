package com.example.tmsxmlproject.domain

interface Repository {

    fun addItem(item: ItemModel)
    fun removeItem(position: Int)
    fun getItems(): List<ItemModel>
}