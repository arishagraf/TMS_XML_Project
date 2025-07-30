package com.example.tmsxmlproject.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.R
import com.example.tmsxmlproject.data.RepositoryImpl
import com.example.tmsxmlproject.databinding.ActivityHomeTask22Binding
import com.example.tmsxmlproject.domain.AddItemUseCase
import com.example.tmsxmlproject.domain.GetItemListUseCase
import com.example.tmsxmlproject.domain.RemoveItemUseCase
import com.google.android.material.snackbar.Snackbar


class HomeTask22 : AppCompatActivity() {
    private lateinit var binding: ActivityHomeTask22Binding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeTask22Binding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = MyViewModel(
            addItemUseCase = AddItemUseCase(RepositoryImpl),
            removeItemUseCase = RemoveItemUseCase(RepositoryImpl),
            getItemListUseCase = GetItemListUseCase(RepositoryImpl)
        )

        val myAdapter = MyAdapter(emptyList(), { position ->
            myViewModel.removeItem(position)
        })

        binding.list.adapter = myAdapter
        binding.list.layoutManager = LinearLayoutManager(this)

        myViewModel.listLiveData.observe(this, Observer { list ->
            myAdapter.updateList(list)
        })

        myViewModel.showList.observe(this, {
            binding.list.visibility = it
        })

        myViewModel.showEmptyState.observe(this, {
            binding.noDataText.visibility = it
        })

        myViewModel.msgLiveData.observe(this, { msg ->
            Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
        })

        binding.addItem.setOnClickListener {
            val str = binding.newItemText.text.toString()
            myViewModel.addItem(str)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}