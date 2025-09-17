package com.example.tmsxmlproject.networking.data.posts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsDAO {

    @Query("SELECT * FROM posts")
    fun getAllEntities(): Flowable<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<PostEntity>)

    @Update
    fun updateEntity(entity: PostEntity)

    @Query("DELETE FROM posts WHERE id = :id")
    fun deleteEntity(id: String)

    @Query("SELECT COUNT(*) FROM posts")
    fun getPostsSize(): Single<Int>
}