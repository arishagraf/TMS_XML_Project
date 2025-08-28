package com.example.tmsxmlproject.networking.presentation.posts

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmsxmlproject.networking.data.posts.Post
import com.example.tmsxmlproject.networking.domain.posts.DeletePostByIdUseCase
import com.example.tmsxmlproject.networking.domain.posts.EditPostUseCase
import com.example.tmsxmlproject.networking.domain.posts.GetEditedTitleListUseCase
import com.example.tmsxmlproject.networking.domain.posts.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostByIdUseCase: DeletePostByIdUseCase,
    private val editPostUseCase: EditPostUseCase,
    private val getEditedTitleListUseCase: GetEditedTitleListUseCase
) : ViewModel() {

    //post is not correct to use here!
    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> get() = _posts

    private val _image = MutableLiveData<Drawable?>()
    val imageLD: LiveData<Drawable?> get() = _image

    private val _shouldNavigateNext = MutableLiveData<Boolean>(false)
    val shouldNavigateNext: LiveData<Boolean> get() = _shouldNavigateNext

    private val _shouldNavigateAddScreen = MutableLiveData<Boolean>(false)
    val shouldNavigateAddScreen: LiveData<Boolean> get() = _shouldNavigateAddScreen

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }

    init {
        getCurrentPosts()
    }

    fun getLists() {
        viewModelScope.launch {
            val result = getEditedTitleListUseCase.invoke()
            println("edited Titles: $result")
        }
    }

    private fun getCurrentPosts() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val posts = getPostsUseCase.invoke()
            posts?.collect {
                _posts.value = it
            } ?: run {
                _msg.value = "no posts found"
            }
        }
    }

    fun deletePost(id: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val result = deletePostByIdUseCase.invoke(id)
            if (result) {
                _msg.value = "deleted"
            } else {
                _msg.value = "not deleted"
            }
        }
    }

    fun editPost(editedPost: Post) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val updatedPost = editPostUseCase.invoke(editedPost)
            _msg.value = updatedPost.toString()
        }
    }

    fun onGoToNextExampleClicked() {
        _shouldNavigateNext.value = true
    }

    fun goToPostClicked() {
        _shouldNavigateAddScreen.value = true
    }
}