package com.example.tmsxmlproject.data

import com.example.tmsxmlproject.domain.ItemModel
import com.example.tmsxmlproject.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

object RepositoryImpl : Repository {

    private val itemList = mutableListOf<ItemEntity>()

    override suspend fun addItem(item: ItemModel) {
        withContext(Dispatchers.IO) {
            itemList.add(ItemEntity(text = item.text, date = Date().toString()))
        }
    }

    override suspend fun removeItem(position: Int) {
        withContext(Dispatchers.IO) {
            itemList.removeAt(position)
        }
    }

    override suspend fun getItems(): List<ItemModel> {
        return withContext(Dispatchers.IO) {
            itemList.map {
                ItemModel(it.text)
            }
        }
    }
}