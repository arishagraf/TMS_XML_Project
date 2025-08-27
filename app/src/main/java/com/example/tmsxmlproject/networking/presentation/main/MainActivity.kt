package com.example.tmsxmlproject.networking.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tmsxmlproject.databinding.ActivityMainBinding
import com.example.tmsxmlproject.networking.presentation.onboarding.OnboardingActivity
import com.example.tmsxmlproject.networking.presentation.posts.PostsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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