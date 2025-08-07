package com.example.tmsxmlproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmsxmlproject.domain.AddItemUseCase
import com.example.tmsxmlproject.domain.GetItemListUseCase
import com.example.tmsxmlproject.domain.ItemModel
import com.example.tmsxmlproject.domain.RemoveItemUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        println("exceptionHandler: $e")
    }

    init {
        checkScreenState()
    }

    fun addItem(str: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            if (str.isNotEmpty()) {
                addItemUseCase.invoke(str)
                _msgLiveData.value = "Добавлен новый элемент"
            } else {
                _msgLiveData.value = "Введите текст в поле для ввода"
            }
            getList()
        }
    }

    fun removeItem(position: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            removeItemUseCase.invoke(position)
            getList()
        }
    }

    private fun getList() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val itemList = getItemListUseCase()
            _listLiveData.value = itemList
            checkScreenState()
        }
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