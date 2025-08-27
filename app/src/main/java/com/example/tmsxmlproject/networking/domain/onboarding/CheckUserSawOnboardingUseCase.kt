package com.example.tmsxmlproject.networking.domain.onboarding

import javax.inject.Inject

class CheckUserSawOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke() = onboardingRepository.checkUserSawOnboarding()
}