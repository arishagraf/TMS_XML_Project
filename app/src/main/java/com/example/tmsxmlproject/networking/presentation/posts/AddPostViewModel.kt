package com.example.tmsxmlproject.networking.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(

) : ViewModel() {

    private val _title = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()

    fun onTitleChanged(text: String) {
        _title.value = text
    }

    fun onDescriptionChanged(text: String) {
        _description.value = text
    }
}