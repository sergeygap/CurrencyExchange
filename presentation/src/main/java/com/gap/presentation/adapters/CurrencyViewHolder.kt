package com.gap.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.gap.domain.entities.Valute
import com.gap.presentation.databinding.ItemCurrencyBinding

class CurrencyViewHolder(private val binding: ItemCurrencyBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(currency: Valute) {
        binding.tvNameCurrency.text = currency.name
        binding.tvNameCurrencyCode.text = currency.charCode
        binding.tvCurrencyValue.text = currency.value.toString()

    }
}