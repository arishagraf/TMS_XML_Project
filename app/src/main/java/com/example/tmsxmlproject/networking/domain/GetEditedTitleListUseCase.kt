package com.example.tmsxmlproject.networking.domain

import javax.inject.Inject

class GetEditedTitleListUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke() = postRepository.getList()
}