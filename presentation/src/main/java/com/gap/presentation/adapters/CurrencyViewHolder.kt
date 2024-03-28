package com.gap.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gap.domain.entities.Valute
import com.gap.presentation.R
import com.gap.presentation.databinding.ItemCurrencyBinding
import java.util.Locale

class CurrencyViewHolder(private val binding: ItemCurrencyBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(currency: Valute) {
        binding.tvNameCurrency.text = currency.name
        binding.tvNameCurrencyCode.text = currency.charCode
        binding.tvCurrencyValue.text = context.getString(R.string.currency_value, currency.value.toString())
        binding.tvZaHardcode.text = spannableString(currency.nominal)
        binding.tvStateCurrency.text = context.getString(R.string.currency_difference, currency.difference.toString())
        binding.ivStateCurrency.setImageResource(differenceState(currency.difference))
        binding.ivMain.setImageResource(chooseFlag(currency.charCode))
    }

    @SuppressLint("DiscouragedApi")
    private fun chooseFlag(charCode: String): Int {
        if (charCode == "TRY") {
            return R.drawable.resource_try
        }
        val resources = itemView.context.resources
        return resources.getIdentifier(
            charCode.lowercase(Locale.getDefault()),
            "drawable",
            itemView.context.packageName
        )
    }

    private fun differenceState(state: Double): Int {
        return if (state >= 0) {
            R.drawable.up_24
        } else {
            R.drawable.down_24
        }
    }

    private fun spannableString(nominal: Int): SpannableString {
        val nominalText = context.getString(R.string.za_hardcode, nominal)
        val spannableText = SpannableString(nominalText)
        val startIndex = nominalText.indexOf(nominal.toString())
        val endIndex = startIndex + nominal.toString().length
        spannableText.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableText
    }
}