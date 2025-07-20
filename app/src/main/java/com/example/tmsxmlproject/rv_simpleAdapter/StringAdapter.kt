package com.example.tmsxmlproject.rv_simpleAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmsxmlproject.R

class StringAdapter(
    private val items: MutableList<String>,
    private val recyclerViewClickListener: RecyclerViewClickListener,
) : RecyclerView.Adapter<StringAdapter.StringViewHolder>() {

    inner class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textItem: TextView = itemView.findViewById(R.id.textItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_layout, parent, false)
        return StringViewHolder(view)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.textItem.text = items[position]
        holder.textItem.setOnClickListener {
            recyclerViewClickListener.onClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}
