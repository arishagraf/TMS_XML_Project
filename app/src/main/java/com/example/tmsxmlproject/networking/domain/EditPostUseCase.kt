package com.example.tmsxmlproject.networking.domain

import com.example.tmsxmlproject.networking.data.Post

class EditPostUseCase(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke(editedPost: Post) = postRepository.updatePost(
        editedPost.id,
        editedPost
    )
}