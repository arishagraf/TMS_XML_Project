package com.example.tmsxmlproject.rv_listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.tmsxmlproject.databinding.ItemStringBinding

class HorizontalStringAdapter(
    private val onItemClick: (String) -> Unit,
) : ListAdapter<String, StringViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StringViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
