package com.example.tmsxmlproject.data

import com.example.tmsxmlproject.domain.ItemModel
import com.example.tmsxmlproject.domain.Repository
import java.util.Date

object RepositoryImpl : Repository {

    private val itemList = mutableListOf<ItemEntity>()

    override fun addItem(item: ItemModel) {
        itemList.add(ItemEntity(text = item.text, date = Date().toString()))
    }

    override fun removeItem(position: Int) {
        itemList.removeAt(position)
    }

    override fun getItems(): List<ItemModel> = itemList.map {
        ItemModel(it.text)
    }
}