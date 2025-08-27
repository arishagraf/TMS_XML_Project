package com.example.tmsxmlproject.networking.data.onboarding

import com.example.tmsxmlproject.networking.data.sharedPrefs.DataStoreManager
import com.example.tmsxmlproject.networking.data.sharedPrefs.SharedPreferenceManager
import com.example.tmsxmlproject.networking.domain.onboarding.OnboardingRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val dataStoreManager: DataStoreManager,
) : OnboardingRepository {

    override suspend fun checkUserSawOnboarding(): Boolean {
        //return sharedPreferenceManager.checkOnboardingWasSeen()
        return dataStoreManager.wasOnboardingSeen().first()
    }

    override suspend fun setUserSawOnboarding(wasSeen: Boolean) {
       // sharedPreferenceManager.setOnboardingWasSeen(wasSeen)
        dataStoreManager.setUserSawOnboarding(wasSeen)
    }
}