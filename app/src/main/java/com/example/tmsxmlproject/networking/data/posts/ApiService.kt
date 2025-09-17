package com.example.tmsxmlproject.networking.data.posts

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    fun fetchPosts(): Single<List<Post>>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") postId: String): Single<Response<Unit>>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") postId: String, @Body updatedPost: Post): Single<Post>

    //path - это путь - /...../...../...../...../
    //body - это тело запроса, которое мы отправляем на сервер
    //query - это поиск
}