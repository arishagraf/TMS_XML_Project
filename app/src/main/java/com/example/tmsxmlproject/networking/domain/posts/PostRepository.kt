package com.example.tmsxmlproject.networking.domain.posts

import com.example.tmsxmlproject.networking.data.posts.Post
import com.example.tmsxmlproject.networking.data.posts.PostEntity
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface PostRepository {

      fun getPostsFromApiAndSaveInBD(): Single<List<PostEntity>>
      fun getPostsFromDB(): Flowable<List<Post>>
      fun deletePost(postId: String): Single<Boolean>
      fun updatePost(postId: String, updatedPost: Post): Single<Post>

       fun getList(): List<String>
}