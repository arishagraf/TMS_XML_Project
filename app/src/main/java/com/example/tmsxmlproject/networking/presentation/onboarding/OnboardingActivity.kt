package com.example.tmsxmlproject.networking.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tmsxmlproject.databinding.ActivityOnboardingBinding
import com.example.tmsxmlproject.networking.presentation.posts.PostsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnLetsStart.setOnClickListener {
            viewModel.letsStartClicked()
        }

        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                if (it) {
                    startActivity(Intent(this@OnboardingActivity, PostsActivity::class.java))
                }
            }
        }
    }
}