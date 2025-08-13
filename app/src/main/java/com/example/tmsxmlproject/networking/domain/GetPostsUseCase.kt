package com.example.tmsxmlproject.networking.domain

class GetPostsUseCase(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke() = postRepository.fetchPosts()
}