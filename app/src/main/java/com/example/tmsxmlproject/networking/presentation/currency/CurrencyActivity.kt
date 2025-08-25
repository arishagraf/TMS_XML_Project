package com.example.tmsxmlproject.networking.presentation.currency

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.databinding.ActivityCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyBinding
    private val currencyViewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencyAdapter = CurrencyAdapter(items = emptyList())

        binding.recyclerView.adapter = currencyAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            currencyViewModel.stateFlow.collect {
                currencyAdapter.updateList(it ?: emptyList())
            }
        }
    }
}