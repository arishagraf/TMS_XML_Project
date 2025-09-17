package com.example.tmsxmlproject.networking.presentation.posts

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmsxmlproject.R
import com.example.tmsxmlproject.networking.data.posts.Post
import com.example.tmsxmlproject.networking.domain.posts.DeletePostByIdUseCase
import com.example.tmsxmlproject.networking.domain.posts.EditPostUseCase
import com.example.tmsxmlproject.networking.domain.posts.GetEditedTitleListUseCase
import com.example.tmsxmlproject.networking.domain.posts.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
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

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> get() = _msg

    private val compositeDisposable = CompositeDisposable()

    init {
        getCurrentPosts()
    }

    fun getLists() {
        val result = getEditedTitleListUseCase.invoke()
        println("edited Titles: $result")
    }

    private fun getCurrentPosts() {
        val posts = getPostsUseCase.invoke()
        val disposable = posts.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _posts.value = it },
                { _msg.value = R.string.no_posts_found },
            )
        compositeDisposable.add(disposable)
    }

    fun deletePost(id: String) {
        val result = deletePostByIdUseCase.invoke(id)
        val disposable = result.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it) {
                    _msg.value = R.string.deleted
                } else {
                    _msg.value = R.string.not_deleted
                }
            }, {
                _msg.value = R.string.not_deleted
            })
        compositeDisposable.add(disposable)
    }

    fun editPost(editedPost: Post) {
//        val updatedPost = editPostUseCase.invoke(editedPost)
//        _msg.value = 0//updatedPost.toString()
    }

    fun onGoToNextExampleClicked() {
        _shouldNavigateNext.value = true
    }

    fun goToPostClicked() {
        _shouldNavigateAddScreen.value = true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}