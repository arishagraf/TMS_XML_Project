package com.example.tmsxmlproject.networking.presentation.currency

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.databinding.ActivityCurrencyBinding
import com.example.tmsxmlproject.networking.App
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityCurrencyBinding
    private val currencyViewModel: CurrencyViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as App).provideAppComponent().inject(this)
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