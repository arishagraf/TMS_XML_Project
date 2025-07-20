package com.example.tmsxmlproject.rv_listAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.tmsxmlproject.databinding.ItemStringBinding

class StringViewHolder(
    private val binding: ItemStringBinding,
    private val onItemClick: (String) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.textItem.text = item
        binding.root.setOnClickListener {
            onItemClick(item)  // Trigger the click callback
        }
    }
}