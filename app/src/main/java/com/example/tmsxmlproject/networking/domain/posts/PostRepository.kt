package com.example.tmsxmlproject.networking.domain.posts

import com.example.tmsxmlproject.networking.data.posts.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun fetchPosts(): Flow<List<Post>>?
    suspend fun deletePost(postId: String): Boolean
    suspend fun updatePost(postId: String, updatedPost: Post): Post?

    suspend fun getList(): List<String>
}