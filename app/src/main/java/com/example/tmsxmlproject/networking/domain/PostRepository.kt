package com.example.tmsxmlproject.networking.domain

import com.example.tmsxmlproject.networking.data.Post

interface PostRepository {
    suspend fun fetchPosts(): List<Post>?
    suspend fun deletePost(postId: String): Boolean
    suspend fun updatePost(postId: String, updatedPost: Post): Post?

    suspend fun getList(): List<String>
}