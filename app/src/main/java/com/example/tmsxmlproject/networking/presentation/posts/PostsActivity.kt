package com.example.tmsxmlproject.networking.presentation.posts

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.tmsxmlproject.databinding.ActivityPostsBinding
import com.example.tmsxmlproject.networking.domain.posts.PostRepository
import com.example.tmsxmlproject.networking.presentation.currency.CurrencyActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.tmsxmlproject.R

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostsBinding
    private val viewModel: PostsViewModel by viewModels()

    @Inject
    lateinit var postRepository: PostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Toast.makeText(this, getText(it), Toast.LENGTH_SHORT).show()
        })

        viewModel.shouldNavigateNext.observe(this, {
            if (it) {
                val intent = Intent(this, CurrencyActivity::class.java)
                startActivity(intent)
                Animatoo.animateSlideUp(this)
            }
        })

        viewModel.shouldNavigateAddScreen.observe(this, {
            if (it) {
                val intent = Intent(this, AddPostActivity::class.java)
                val options = ActivityOptions.makeCustomAnimation(
                    this,
                    R.anim.slide_right,
                    R.anim.slide_left
                )
                startActivity(intent, options.toBundle())
                //overridePendingTransition(R.anim.slide_right, R.anim.slide_left)
            }
        })

        binding.goToNextExample.setOnClickListener {
            viewModel.onGoToNextExampleClicked()
        }

        binding.btnAddPost.setOnClickListener {
            viewModel.goToPostClicked()
        }
    }
}