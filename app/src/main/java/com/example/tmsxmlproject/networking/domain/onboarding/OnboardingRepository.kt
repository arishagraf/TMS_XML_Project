package com.example.tmsxmlproject.networking.domain.onboarding

interface OnboardingRepository {

    suspend fun checkUserSawOnboarding(): Boolean
    suspend fun setUserSawOnboarding(wasSeen: Boolean)
}