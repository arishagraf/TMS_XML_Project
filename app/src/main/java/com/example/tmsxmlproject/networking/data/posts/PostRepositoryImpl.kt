package com.example.tmsxmlproject.networking.data.posts

import com.example.tmsxmlproject.networking.data.helper.InternetConnectionManager
import com.example.tmsxmlproject.networking.domain.posts.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val postsDAO: PostsDAO,
    private val connectionManager: InternetConnectionManager,
) : PostRepository {

    private var listOfEditedItems = mutableListOf<String>()

    override suspend fun getList(): List<String> {
        return listOfEditedItems
    }

    override suspend fun fetchPosts(): Flow<List<Post>>? = withContext(Dispatchers.IO) {
        try {
            if (postsDAO.getPostsSize() <= 0) {
                if (connectionManager.isOnline()) {
                    val apiPosts = apiService.fetchPosts()
                    val postEntities = apiPosts?.map { apiPost ->
                        PostEntity(
                            id = apiPost.id,
                            userId = apiPost.userId,
                            title = apiPost.title,
                            body = apiPost.body
                        )
                    }
                    postEntities?.let {
                        postsDAO.insertAll(postEntities)
                    }
                    val entities = postsDAO.getAllEntities()
                    entities.map { flowItem ->
                        flowItem.map { entity ->
                            Post(
                                id = entity.id,
                                userId = entity.userId,
                                title = entity.title,
                                body = entity.body
                            )
                        }
                    }
                } else {
                    null
                }
            } else {
                val entities = postsDAO.getAllEntities()
                entities.map { flowItem ->
                    flowItem.map { entity ->
                        Post(
                            id = entity.id,
                            userId = entity.userId,
                            title = entity.title,
                            body = entity.body
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deletePost(postId: String): Boolean = withContext(Dispatchers.IO) {
        postsDAO.deleteEntity(postId)
        try {
            if (connectionManager.isOnline()) {
                val result = apiService.deletePost(postId)
                result.isSuccessful
            } else {
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updatePost(postId: String, updatedPost: Post): Post? =
        withContext(Dispatchers.IO) {
            listOfEditedItems.add(updatedPost.title)
            postsDAO.updateEntity(
                PostEntity(
                    id = updatedPost.id,
                    userId = updatedPost.userId,
                    title = updatedPost.title,
                    body = updatedPost.body
                )
            )
            try {
                if (connectionManager.isOnline()) {
                    val result = apiService.updatePost(postId, updatedPost)
                    result
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}