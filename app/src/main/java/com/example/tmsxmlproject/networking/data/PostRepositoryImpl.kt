package com.example.tmsxmlproject.networking.data

import com.example.tmsxmlproject.networking.domain.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : PostRepository {

    private var listOfEditedItems = mutableListOf<String>()

    override suspend fun getList(): List<String> {
        return listOfEditedItems
    }


    //we can use https://jsonplaceholder.typicode.com/posts - but it will not make real changes
    //so we are going to create our own api - https://mockapi.io/

    override suspend fun fetchPosts(): List<Post>? = withContext(Dispatchers.IO) {
        try {
            apiService.fetchPosts()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deletePost(postId: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val result = apiService.deletePost(postId)
            result.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updatePost(postId: String, updatedPost: Post): Post? =
        withContext(Dispatchers.IO) {
            listOfEditedItems.add(updatedPost.title)
            try {
                apiService.updatePost(postId, updatedPost)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}