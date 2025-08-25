package com.example.tmsxmlproject.networking.domain.posts

import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke() = postRepository.fetchPosts()
}