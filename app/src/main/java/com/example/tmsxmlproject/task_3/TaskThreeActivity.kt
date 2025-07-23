package com.example.tmsxmlproject.task_3

import android.os.Bundle
import com.example.tmsxmlproject.databinding.ActivityTaskThreeBinding
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class TaskThreeActivity : MvpAppCompatActivity(), TimerView {

    private lateinit var binding: ActivityTaskThreeBinding

    @InjectPresenter //this is when presenter is without parameters
    lateinit var presenter: TimerPresenter

    // if you pass parameters into it
    @ProvidePresenter
    fun providePresenter(): TimerPresenter = TimerPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            presenter.startTimer(10)
        }
    }

    override fun updateTimer(text: String) {
        binding.timerText.text = text
    }
}
