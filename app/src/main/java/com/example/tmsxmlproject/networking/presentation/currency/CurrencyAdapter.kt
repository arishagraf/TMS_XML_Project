package com.example.tmsxmlproject.networking.presentation.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmsxmlproject.databinding.ItemCurrencyBinding
import com.example.tmsxmlproject.networking.data.currency.CurrencyModel

class CurrencyAdapter(
    private val items: List<CurrencyModel>,
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    private var itemList: List<CurrencyModel> = items

    class CurrencyViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CurrencyModel) {
            binding.currencyName.text = item.curName
            binding.currencyScale.text = item.curScale.toString()
            binding.currencyRate.text = item.curOfficialRate.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = ItemCurrencyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.onBind(itemList[position])

    }

    fun updateList(newItemList: List<CurrencyModel>) {
        itemList = newItemList
        notifyDataSetChanged()
    }
}