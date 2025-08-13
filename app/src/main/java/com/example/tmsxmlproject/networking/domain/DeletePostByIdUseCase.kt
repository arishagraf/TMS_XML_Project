package com.example.tmsxmlproject.networking.domain

class DeletePostByIdUseCase(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke(id: String) = postRepository.deletePost(id)
}