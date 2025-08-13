package com.example.tmsxmlproject.networking.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmsxmlproject.networking.data.Post
import com.example.tmsxmlproject.networking.domain.DeletePostByIdUseCase
import com.example.tmsxmlproject.networking.domain.EditPostUseCase
import com.example.tmsxmlproject.networking.domain.GetPostsUseCase
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostByIdUseCase: DeletePostByIdUseCase,
    private val editPostUseCase: EditPostUseCase,
) : ViewModel() {

    //post is not correct to use here!
    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> get() = _posts

    private val _shouldNavigateNext = MutableLiveData<Boolean>(false)
    val shouldNavigateNext: LiveData<Boolean> get() = _shouldNavigateNext

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    init {
        viewModelScope.launch {
            val posts = getPostsUseCase.invoke()
            posts?.let {
                _posts.value = it
            } ?: run {
                _msg.value = "no posts found"
            }
        }
    }

    fun deletePost(id: String) {
        viewModelScope.launch {
            val result = deletePostByIdUseCase.invoke(id)
            if (result) {
                _msg.value = "deleted"
            } else {
                _msg.value = "not deleted"
            }
        }
    }

    fun editPost(editedPost: Post){
        viewModelScope.launch {
            val updatedPost = editPostUseCase.invoke(editedPost)
            _msg.value = updatedPost.toString()
        }
    }

    fun onGoToNextExampleClicked() {
        _shouldNavigateNext.value = true
    }
}