package com.example.tmsxmlproject.scroll_nested_views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmsxmlproject.databinding.ItemStringBinding

class HorizontalScrollNestedStringAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<String, HorizontalScrollNestedStringAdapter.StringViewHolder>(DiffCallback()) {

    inner class StringViewHolder(private val binding: ItemStringBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.textItem.text = item
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
}
