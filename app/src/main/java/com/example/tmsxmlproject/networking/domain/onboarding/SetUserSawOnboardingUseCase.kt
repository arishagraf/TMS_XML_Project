package com.example.tmsxmlproject.networking.domain.onboarding

import javax.inject.Inject

class SetUserSawOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke(wasSeen: Boolean) =
        onboardingRepository.setUserSawOnboarding(wasSeen)
}