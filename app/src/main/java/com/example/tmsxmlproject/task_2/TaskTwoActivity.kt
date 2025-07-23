package com.example.tmsxmlproject.task_2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.databinding.ActivityTaskTwoBinding
import com.example.tmsxmlproject.task_3.TaskThreeActivity

class TaskTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskTwoBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTaskTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(emptyList())
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.adapter = adapter

        // Observe ViewState
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect { state ->
                when (state.userViewState) {
                    is UserViewState.Content -> {
                        adapter.updateList(state.userViewState.users)
                    }

                    is UserViewState.NavigateNext -> { //this will also cause rotation issues,
                        //because StateFlow emits the latest value to new subscribers.
                        startActivity(
                            Intent(
                                this@TaskTwoActivity,
                                TaskThreeActivity::class.java
                            )
                        )
                    }
                    // state.userViewState.? - no sideeffect available in states
                }
                Toast.makeText(
                    this@TaskTwoActivity, state.sideEffectMessage, Toast.LENGTH_SHORT
                ).show()
            }
        }


        // Send Intent on filter
        binding.filterButton.setOnClickListener {
            val query = binding.filterEditText.text.toString()
            viewModel.sendIntent(UserIntent.FilterUsers(query))
        }

        binding.goToNextExample.setOnClickListener {
            viewModel.sendIntent(UserIntent.GoToNextScreen)
        }
    }
}
