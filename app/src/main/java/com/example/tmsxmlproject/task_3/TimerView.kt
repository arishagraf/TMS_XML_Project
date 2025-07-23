package com.example.tmsxmlproject.task_3

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TimerView : MvpView {
    fun updateTimer(text: String)
}
