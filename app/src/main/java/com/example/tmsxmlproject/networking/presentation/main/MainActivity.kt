package com.example.tmsxmlproject.networking.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tmsxmlproject.databinding.ActivityMainBinding
import com.example.tmsxmlproject.networking.App
import com.example.tmsxmlproject.networking.presentation.onboarding.OnboardingActivity
import com.example.tmsxmlproject.networking.presentation.posts.PostsActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels{viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as App).provideAppComponent().inject(this)
        enableEdgeToEdge()
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        lifecycleScope.launch {
            viewModel.stateFlow.collect { wasSeen ->
                if (wasSeen) {
                    startActivity(Intent(this@MainActivity, PostsActivity::class.java))
                } else {
                    startActivity(Intent(this@MainActivity, OnboardingActivity::class.java))
                }
            }
        }
    }
}