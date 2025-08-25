package com.example.tmsxmlproject.networking.data.posts

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun fetchPosts(): List<Post>?

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: String): Response<Unit>

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId: String, @Body updatedPost: Post): Post?

    //path - это путь - /...../...../...../...../
    //body - это тело запроса, которое мы отправляем на сервер
    //query - это поиск
}