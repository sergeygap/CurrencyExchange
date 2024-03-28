package com.gap.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.gap.presentation.databinding.ItemCurrencyBinding

class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {
    val differ = AsyncListDiffer(this, CurrencyDiffUtil())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding, parent.context)
    }

    override fun getItemCount() = differ.currentList.size


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val valute = differ.currentList[position]
        holder.bind(valute)
    }
}