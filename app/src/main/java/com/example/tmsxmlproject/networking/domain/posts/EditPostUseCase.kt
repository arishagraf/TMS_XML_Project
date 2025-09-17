package com.example.tmsxmlproject.networking.domain.posts

import com.example.tmsxmlproject.networking.data.posts.Post
import javax.inject.Inject

class EditPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val auto: Auto, // this is for test
) {

     operator fun invoke(editedPost: Post) = postRepository.updatePost(
        editedPost.id,
        editedPost
    )
}

class Auto @Inject constructor(val engine: Engine)
class Engine //this uses no inject constr, because its created in module