package com.example.tmsxmlproject.task_1.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tmsxmlproject.databinding.ActivityTaskOneBinding
import com.example.tmsxmlproject.task_1.data.CounterRepositoryImpl
import com.example.tmsxmlproject.task_1.domain.GetCountUseCase
import com.example.tmsxmlproject.task_2.TaskTwoActivity

class TaskOneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskOneBinding
    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTaskOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = CounterViewModel(GetCountUseCase(CounterRepositoryImpl()))

        viewModel.counter.observe(this, Observer { count ->
            binding.counterText.text = "Нажатий: $count"
        })

        viewModel.msg.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
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