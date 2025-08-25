package com.example.tmsxmlproject.networking.domain.posts

import com.example.tmsxmlproject.networking.data.posts.Post

interface PostRepository {
    suspend fun fetchPosts(): List<Post>?
    suspend fun deletePost(postId: String): Boolean
    suspend fun updatePost(postId: String, updatedPost: Post): Post?

    suspend fun getList(): List<String>
}