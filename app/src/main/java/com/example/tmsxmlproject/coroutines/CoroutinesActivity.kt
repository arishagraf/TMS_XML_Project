package com.example.tmsxmlproject.coroutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tmsxmlproject.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutinesBinding
    private val viewModel: CoroutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //not nessesary to use it when working with ui elements!
        //  lifecycleScope.launch {
        binding.textView.text = "new text"
        // }

        //for activity, fragment, composable funs - cancels itself when view dies
        lifecycleScope.launch {
            viewModel.stateflow.collect {
                binding.textView.text = it.toString()
            }
        }

        //viewModelScope - we use in Viewmodels - cancels itself when viewModel calls onCleared()

        //no lifecycle
        CoroutineScope(Dispatchers.Main).launch {
            binding.textView.text = "new text"
        }

        //coroutineExceptionHandler - must have everywhere!! On parent
        lifecycleScope.launch(CoroutineExceptionHandler { _, _ ->
            println("hello: exception")
        }) {
            launch {
                //supervisorScope - we use to prevent our childs failing when
                //one of the child fails with exception + we must
                // use on parent coroutineExceptionHandler when using supervisor
                supervisorScope {
                    launch {
                        throw Exception()
                    }
                    launch {
                        println("hello from child 3")
                    }
                }
            }
            launch {
                println("hello from child 2")
            }
        }

        coldFlow()
    }

    private fun coldFlow() {
        lifecycleScope.launch {
            viewModel.flow.collect {
                println("coldFlow1: $it")
            }
        }
    }

    private fun stateFlowExample() {
        lifecycleScope.launch {
            viewModel.stateflow.collect {
                println("stateflow: $it")
            }
        }
    }

    private fun sharedFlowExample() {
        lifecycleScope.launch {
            viewModel.sharedFlow.collect {
                println("sharedFlow: $it")
            }
        }
    }

    //flow - cold
    //shared/state - hot
    private fun flowExamples() {
        val flowStrings = flow<List<String>> {
            emit(listOf("one", "two", "three", "four", "five", "six"))
            emit(listOf("seven", "eight", "nine", "ten", "eleven", "twelve"))
        }

        val flowInts = flow<List<Int>> {
            emit(listOf(1, 2, 3))
            emit(listOf(4, 5, 6))
            emit(listOf(7, 8, 9))
        }

        //zip - smallest one
        //combine - takes the latest known value from the second flow
        lifecycleScope.launch {
            val fullList = flowInts.zip(flowStrings) { ints, strings ->
                // we can make transformation here
                "$strings $ints"
            }
            fullList.collect { //we call collect to observe data from flow
                println("datadata: $it")
            }
        }
    }

    private fun sequently() {
        lifecycleScope.launch {
            launch {
                for (i in 1..10) {
                    println("job1: $i")
                }
            }
            launch {
                for (i in 11..20) {
                    println("job2: $i")
                }
            }
        }
    }

    private fun parallel() {
        lifecycleScope.launch {
            launch {
                for (i in 1..10) {
                    println("job1: $i")
                    yield() // to make parallel
                }
            }
            launch {
                for (i in 11..20) {
                    println("job2: $i")
                    yield() // to make parallel
                }
            }
        }
    }

    private fun sequentlyAsync() {
        lifecycleScope.launch {
            val result1 = async {
                for (i in 1..10) {
                    println("job1: $i")
                }
            }
            val result2 = async {
                for (i in 11..20) {
                    println("job2: $i")
                }
            }
            result1.await()
            result2.await()
        }
    }

    private fun parallelAsync() {
        lifecycleScope.launch {
            val result1 = async {
                for (i in 1..100) {
                    println("job1: $i")
                    yield() // to make parallel
                }
            }

            val result2 = async {
                for (i in 110..200) {
                    println("job2: $i")
                    yield() // to make parallel
                }
            }

            val result3 = async { // async returns Deferred result
                for (i in 210..300) {
                    println("job3: $i")
                    yield() // to make parallel
                }
            }
            result1.await() //we call await on Deferred to receive value from async
            result2.await()
            result3.await()
        }
    }
}