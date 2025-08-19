package com.example.tmsxmlproject.networking.presentation.posts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.databinding.ActivityPostsBinding
import com.example.tmsxmlproject.networking.domain.PostRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostsBinding
    private val viewModel: PostsViewModel by viewModels()

    @Inject
    lateinit var postRepository: PostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        println("hashcode is: ${postRepository.hashCode()}")

        viewModel.getLists()
        val postAdapter = PostsAdapter(
            items = emptyList(),
            removeAction = { id -> viewModel.deletePost(id) },
            editAction = { editedPost -> viewModel.editPost(editedPost) }
        )

        binding.recyclerView.adapter = postAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.posts.observe(this, { list ->
            postAdapter.updateList(list)
        })

        viewModel.msg.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

//        viewModel.shouldNavigateNext.observe(this, {
//            if (it) {
//                val intent = Intent(this, TaskTwoActivity::class.java)
//                startActivity(intent)
//            }
//        })

        binding.goToNextExample.setOnClickListener {
            viewModel.onGoToNextExampleClicked()
        }
    }
}