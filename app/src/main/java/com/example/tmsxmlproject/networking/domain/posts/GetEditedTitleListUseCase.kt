package com.example.tmsxmlproject.networking.domain.posts

import javax.inject.Inject

class GetEditedTitleListUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

      operator fun invoke() = postRepository.getList()
}