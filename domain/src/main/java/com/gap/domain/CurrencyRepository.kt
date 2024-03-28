package com.gap.domain

import com.gap.domain.entities.Currency

interface CurrencyRepository {
    suspend fun getCurrencyList(): Currency
}