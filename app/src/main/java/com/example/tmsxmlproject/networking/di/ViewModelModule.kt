package com.example.tmsxmlproject.networking.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmsxmlproject.networking.presentation.currency.CurrencyViewModel
import com.example.tmsxmlproject.networking.presentation.main.MainViewModel
import com.example.tmsxmlproject.networking.presentation.onboarding.OnboardingViewModel
import com.example.tmsxmlproject.networking.presentation.posts.AddPostViewModel
import com.example.tmsxmlproject.networking.presentation.posts.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun provideCurrencyViewModel(currencyViewModel: CurrencyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    abstract fun provideOnboardingViewModel(onboardingViewModel: OnboardingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddPostViewModel::class)
    abstract fun provideAddPostViewModel(addPostViewModel: AddPostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun providePostsViewModel(postsViewModel: PostsViewModel): ViewModel

}