package com.gap.data.network.dto

import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valute: Map<String, Valute>
)