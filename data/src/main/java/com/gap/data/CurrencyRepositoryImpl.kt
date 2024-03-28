package com.gap.data

import com.gap.data.network.RetrofitInstance

class CurrencyRepositoryImpl {
    private val currencyApi = RetrofitInstance.api

    suspend fun getListCurrency() {
        currencyApi.getListCurrency()
    }

}