package com.example.tmsxmlproject.networking.data.posts

import com.example.tmsxmlproject.networking.domain.posts.PostRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val postsDAO: PostsDAO,
) : PostRepository {

    private var listOfEditedItems = mutableListOf<String>()

    override fun getList(): List<String> {
        return listOfEditedItems
    }

    override fun getPostsFromApiAndSaveInBD(): Single<List<PostEntity>> {
        val apiPosts = apiService.fetchPosts()
       return apiPosts
            .subscribeOn(Schedulers.io())
            .map {
                it.map { apiPost ->
                    PostEntity(
                        id = apiPost.id,
                        userId = apiPost.userId,
                        title = apiPost.title,
                        body = apiPost.body
                    )
                }
            }.doOnSuccess { postEntities ->
                postsDAO.insertAll(postEntities)
            }
    }

    override fun getPostsFromDB(): Flowable<List<Post>> {
        val entities = postsDAO.getAllEntities()
        return entities
            .subscribeOn(Schedulers.io())
            .map {
                it.map { entity ->
                    Post(
                        id = entity.id,
                        userId = entity.userId,
                        title = entity.title,
                        body = entity.body
                    )
                }
            }
    }

    override fun deletePost(postId: String): Single<Boolean> {
        val result = apiService.deletePost(postId)
        return result
            .subscribeOn(Schedulers.io())
            .map { it.isSuccessful }
            .doOnSuccess {
                postsDAO.deleteEntity(postId)
            }
    }

    override fun updatePost(postId: String, updatedPost: Post): Single<Post> {
        val result = apiService.updatePost(postId, updatedPost)
        return result
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                postsDAO.updateEntity(
                    PostEntity(
                        id = updatedPost.id,
                        userId = updatedPost.userId,
                        title = updatedPost.title,
                        body = updatedPost.body
                    )
                )
            }
    }
}