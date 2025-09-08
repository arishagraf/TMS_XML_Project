package com.example.tmsxmlproject.networking.presentation.posts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.tmsxmlproject.databinding.ActivityAddPostBinding
import com.example.tmsxmlproject.networking.App
import javax.inject.Inject

class AddPostActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewBinding: ActivityAddPostBinding
    private val viewModel: AddPostViewModel by viewModels{viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as App).provideAppComponent().inject(this)
        viewBinding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnGoBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        viewBinding.editTextAddTitle.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                viewModel.onTitleChanged(text.toString())
            },
        )

        viewBinding.editTextAddDescription.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                viewModel.onDescriptionChanged(text.toString())
            },
        )
    }
}