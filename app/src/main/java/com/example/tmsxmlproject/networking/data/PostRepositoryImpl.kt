package com.example.tmsxmlproject.networking.data

import android.os.Build
import android.util.Log
import com.example.tmsxmlproject.networking.domain.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepositoryImpl(
    private val retrofitInstance: RetrofitInstance,
) : PostRepository {

    //we can use https://jsonplaceholder.typicode.com/posts - but it will not make real changes
    //so we are going to create our own api - https://mockapi.io/

    override suspend fun fetchPosts(): List<Post>? = withContext(Dispatchers.IO) {
        try {
            retrofitInstance.apiService.fetchPosts()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deletePost(postId: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val result = retrofitInstance.apiService.deletePost(postId)
            result.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updatePost(postId: String, updatedPost: Post): Post? =
        withContext(Dispatchers.IO) {
            try {
                retrofitInstance.apiService.updatePost(postId, updatedPost)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}