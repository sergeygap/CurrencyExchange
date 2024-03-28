package com.gap.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gap.domain.entities.Valute

class CurrencyDiffUtil : DiffUtil.ItemCallback<Valute>() {
    override fun areItemsTheSame(oldItem: Valute, newItem: Valute): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Valute, newItem: Valute): Boolean {
        return oldItem == newItem
    }
}