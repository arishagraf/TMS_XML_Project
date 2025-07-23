package com.example.tmsxmlproject.task_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tmsxmlproject.databinding.ActivityTaskOneBinding
import com.example.tmsxmlproject.task_2.TaskTwoActivity

class TaskOneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskOneBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTaskOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // observe livedata changes
        viewModel.counter.observe(this, Observer { count ->
            binding.counterText.text = "Нажатий: $count"
        })

        viewModel.shouldNavigateNext.observe(this, {
            if (it) {
                val intent = Intent(this, TaskTwoActivity::class.java)
                startActivity(intent)
            }
        })

        binding.incrementButton.setOnClickListener {
            viewModel.incrementCounter()
        }

        binding.goToNextExample.setOnClickListener {
            viewModel.onGoToNextExampleClicked()
        }
    }
}