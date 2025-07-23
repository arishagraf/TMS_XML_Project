package com.example.tmsxmlproject.task_3

import moxy.MvpPresenter
import android.os.CountDownTimer

class TimerPresenter : MvpPresenter<TimerView>() {

    private var countDownTimer: CountDownTimer? = null

    fun startTimer(durationInSeconds: Long = 10) {
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(durationInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                viewState.updateTimer("Осталось: $secondsLeft сек")
            }

            override fun onFinish() {
                viewState.updateTimer("Время вышло!")
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
