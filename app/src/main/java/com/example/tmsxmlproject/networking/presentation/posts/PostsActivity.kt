package com.example.tmsxmlproject.networking.presentation.posts

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.databinding.ActivityPostsBinding
import com.example.tmsxmlproject.networking.App
import com.example.tmsxmlproject.networking.data.helper.InternetConnectionManager
import com.example.tmsxmlproject.networking.domain.posts.PostRepository
import com.example.tmsxmlproject.networking.presentation.broadcastReceiver.TMSBroadcastReceiver
import com.example.tmsxmlproject.networking.presentation.currency.CurrencyActivity
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: PostsViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var postRepository: PostRepository

    lateinit var tmsBroadcastReceiver: BroadcastReceiver

    @Inject
    lateinit var connectionManager: InternetConnectionManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as App).provideAppComponent().inject(this)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tmsBroadcastReceiver = TMSBroadcastReceiver()
        IntentFilter("ACTION_ADD_POST_CLICK").also {
            this.registerReceiver(
                tmsBroadcastReceiver,
                it,
                RECEIVER_EXPORTED //use when android api 33+
            )
        }

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

        viewModel.shouldNavigateNext.observe(this, {
            if (it) {
                val intent = Intent(this, CurrencyActivity::class.java)
                startActivity(intent)
            }
        })

        viewModel.shouldNavigateAddScreen.observe(this, {
            if (it) {
                val intent = Intent(this, AddPostActivity::class.java)
                startActivity(intent)
            }
        })

        binding.goToNextExample.setOnClickListener {
            viewModel.onGoToNextExampleClicked()
        }

        binding.btnAddPost.setOnClickListener {
            viewModel.goToPostClicked()

            val intent = Intent("ACTION_ADD_POST_CLICK")
            intent.putExtra("MY_KEY", "hello I am a message from broadcast")
            this.sendBroadcast(intent)
        }
    }
}