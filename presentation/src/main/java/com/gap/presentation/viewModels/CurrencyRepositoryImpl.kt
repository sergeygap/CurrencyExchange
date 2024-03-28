package com.gap.presentation.viewModels

import android.util.Log
import com.gap.data.mappers.Mapper
import com.gap.data.network.RetrofitInstance
import com.gap.domain.CurrencyRepository
import com.gap.domain.entities.Currency

class CurrencyRepositoryImpl : CurrencyRepository {
    private val currencyApi = RetrofitInstance.api
    private val mapper = Mapper
    override suspend fun getCurrencyList(): Currency {
        return try {
            Log.d(TAG, "working")
            val response = currencyApi.getListCurrency()
            Log.d(TAG, "Response body: ${response.body()}")

            if (!response.isSuccessful) {
                throw Exception("Failed to fetch currency data: ${response.code()}")
            } else {
                val currencyDto = response.body()
                if (currencyDto != null) {
                    mapper.dtoToEntity(currencyDto)
                } else {
                    throw Exception("Response body is null")
                }
            }
        } catch (e: Exception) {
            throw Exception("Exception during network request: ${e.message}", e)
        }
    }

    companion object {
        private const val TAG = "CurrencyRepositoryImpl"
    }

}