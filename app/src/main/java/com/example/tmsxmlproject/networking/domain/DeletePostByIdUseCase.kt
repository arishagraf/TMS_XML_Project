package com.example.tmsxmlproject.networking.domain

import javax.inject.Inject

class DeletePostByIdUseCase @Inject constructor(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke(id: String) = postRepository.deletePost(id)
}