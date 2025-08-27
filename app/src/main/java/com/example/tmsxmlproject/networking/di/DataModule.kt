package com.example.tmsxmlproject.networking.di

import android.content.Context
import com.example.tmsxmlproject.networking.data.currency.Audi
import com.example.tmsxmlproject.networking.data.currency.BMW
import com.example.tmsxmlproject.networking.data.currency.Car
import com.example.tmsxmlproject.networking.data.currency.CurrencyRepositoryImpl
import com.example.tmsxmlproject.networking.data.onboarding.OnboardingRepositoryImpl
import com.example.tmsxmlproject.networking.data.posts.PostRepositoryImpl
import com.example.tmsxmlproject.networking.data.sharedPrefs.DataStoreManager
import com.example.tmsxmlproject.networking.data.sharedPrefs.SharedPreferenceManager
import com.example.tmsxmlproject.networking.domain.currency.CurrencyRepository
import com.example.tmsxmlproject.networking.domain.onboarding.OnboardingRepository
import com.example.tmsxmlproject.networking.domain.posts.PostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    @Binds
    @Singleton
    abstract fun bindCurrencyRepository(
        currencyRepositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository

    companion object {
        @Provides
        fun provideSharedPrefsManager(@ApplicationContext context: Context): SharedPreferenceManager {
            return SharedPreferenceManager(context)
        }

        @Provides
        fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
            return DataStoreManager(context)
        }
    }

    @Binds
    @Singleton
    @Named("audi")
    abstract fun bindAudi(
        audi: Audi
    ): Car

    @Binds
    @Singleton
    abstract fun bindBmw(
        bmw: BMW
    ): Car
}