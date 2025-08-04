package com.example.tmsxmlproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmsxmlproject.domain.AddItemUseCase
import com.example.tmsxmlproject.domain.GetItemListUseCase
import com.example.tmsxmlproject.domain.ItemModel
import com.example.tmsxmlproject.domain.RemoveItemUseCase
import com.example.tmsxmlproject.task_1.data.CounterRepositoryImpl

class MyViewModel(
    private val addItemUseCase: AddItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val getItemListUseCase: GetItemListUseCase,
) : ViewModel() {

    private val _listLiveData = MutableLiveData(listOf<ItemModel>())
    val listLiveData: LiveData<List<ItemModel>> get() = _listLiveData

    private val _msgLiveData = MutableLiveData<String>()
    val msgLiveData: LiveData<String> get() = _msgLiveData

    private val _showList = MutableLiveData<Int>()
    val showList: LiveData<Int> get() = _showList

    private val _showEmptyState = MutableLiveData<Int>()
    val showEmptyState: LiveData<Int> get() = _showEmptyState

    init {
        checkScreenState()
    }

    fun addItem(str: String) {
        if (str.isNotEmpty()) {
            addItemUseCase.invoke(str)
            getList()
            _msgLiveData.value = "Добавлен новый элемент"
        } else {
            _msgLiveData.value = "Введите текст в поле для ввода"
        }
        checkScreenState()
    }

    fun removeItem(position: Int) {
        removeItemUseCase.invoke(position)
        getList()
        checkScreenState()
    }

    private fun getList() {
        val itemList = getItemListUseCase()
        _listLiveData.value = itemList
    }

    private fun checkScreenState() {
        if (listLiveData.value.isEmpty()) {
            _showEmptyState.value = 0
            _showList.value = 8
        } else {
            _showEmptyState.value = 8
            _showList.value = 0
        }
    }

}