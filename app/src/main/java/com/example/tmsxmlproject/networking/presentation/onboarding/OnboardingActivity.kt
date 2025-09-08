package com.example.tmsxmlproject.networking.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tmsxmlproject.databinding.ActivityOnboardingBinding
import com.example.tmsxmlproject.networking.App
import com.example.tmsxmlproject.networking.presentation.posts.PostsActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewBinding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels{viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as App).provideAppComponent().inject(this)
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