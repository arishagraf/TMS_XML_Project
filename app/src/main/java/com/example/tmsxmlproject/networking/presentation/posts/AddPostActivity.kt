package com.example.tmsxmlproject.networking.presentation.posts

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.tmsxmlproject.databinding.ActivityAddPostBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.tmsxmlproject.R

@AndroidEntryPoint
class AddPostActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddPostBinding
    private val viewModel: AddPostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baby_child)

        viewBinding.btnSave.setOnClickListener {
            viewBinding.btnSave.isSelected = !viewBinding.btnSave.isSelected
        }

        viewBinding.btnGoBack?.setOnClickListener {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}