package com.example.tmsxmlproject.networking.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmsxmlproject.networking.data.Post
import com.example.tmsxmlproject.networking.domain.DeletePostByIdUseCase
import com.example.tmsxmlproject.networking.domain.EditPostUseCase
import com.example.tmsxmlproject.networking.domain.GetEditedTitleListUseCase
import com.example.tmsxmlproject.networking.domain.GetPostsUseCase
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

    private val _shouldNavigateNext = MutableLiveData<Boolean>(false)
    val shouldNavigateNext: LiveData<Boolean> get() = _shouldNavigateNext

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }

    init {
        getCurrentPosts()
    }

    fun getLists(){
        viewModelScope.launch {
            val result = getEditedTitleListUseCase.invoke()
            println("edited Titles: $result")
        }
    }

    private fun getCurrentPosts() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val posts = getPostsUseCase.invoke()
            posts?.let {
                _posts.value = it
            } ?: run {
                _msg.value = "no posts found"
            }
        }
    }

    fun deletePost(id: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val result = deletePostByIdUseCase.invoke(id)
            getCurrentPosts()
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
            getCurrentPosts()
            _msg.value = updatedPost.toString()
        }
    }

    fun onGoToNextExampleClicked() {
        _shouldNavigateNext.value = true
    }
}