package com.example.tmsxmlproject.networking.di

import com.example.tmsxmlproject.networking.presentation.currency.CurrencyActivity
import com.example.tmsxmlproject.networking.presentation.main.MainActivity
import com.example.tmsxmlproject.networking.presentation.onboarding.OnboardingActivity
import com.example.tmsxmlproject.networking.presentation.posts.AddPostActivity
import com.example.tmsxmlproject.networking.presentation.posts.PostsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CarModule::class,
        DatabaseModule::class,
        DataModule::class,
        NetworkModule::class,
        ViewModelModule::class,
    ]
)
@FeatureScope
interface AppComponent {
    fun inject(currencyActivity: CurrencyActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(onboardingActivity: OnboardingActivity)
    fun inject(addPostActivity: AddPostActivity)
    fun inject(postsActivity: PostsActivity)
}