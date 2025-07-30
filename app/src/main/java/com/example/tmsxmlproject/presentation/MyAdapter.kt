package com.example.tmsxmlproject.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmsxmlproject.databinding.LayoutItemBinding
import com.example.tmsxmlproject.domain.ItemModel

class MyAdapter(
    private val items: List<ItemModel>,
    val removeAction: (Int) -> Unit,
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var itemList: List<ItemModel> = items

    class MyViewHolder(private val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ItemModel, removeAction: () -> Unit) {
            binding.itemText.text = item.text
            binding.deleteItem.setOnClickListener {
                removeAction()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(itemList[position], { removeAction(position) })

    }

    fun updateList(newItemList: List<ItemModel>) {
        itemList = newItemList
        notifyDataSetChanged()
    }

}