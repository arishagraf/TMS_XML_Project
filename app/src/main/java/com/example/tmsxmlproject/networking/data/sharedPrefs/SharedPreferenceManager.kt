package com.example.tmsxmlproject.networking.data.sharedPrefs

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(ONBOARDING_PREFS, Context.MODE_PRIVATE)

    fun setOnboardingWasSeen(wasSeen: Boolean) {
        sharedPrefs.edit { putBoolean(ONBOARDING_KEY, wasSeen) }
    }

    fun checkOnboardingWasSeen(): Boolean {
        return sharedPrefs.getBoolean(ONBOARDING_KEY, false)
    }

    companion object {
        private const val ONBOARDING_KEY = "onboarding_was_seen"
        private const val ONBOARDING_PREFS = "onboarding"
    }
}