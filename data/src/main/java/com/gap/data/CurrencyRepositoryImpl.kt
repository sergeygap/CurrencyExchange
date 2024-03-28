package com.gap.data

import com.gap.data.mappers.Mapper
import com.gap.data.network.RetrofitInstance
import com.gap.domain.CurrencyRepository
import com.gap.domain.entities.Currency

class CurrencyRepositoryImpl : CurrencyRepository {
    private val currencyApi = RetrofitInstance.api
    private val mapper = Mapper
    override suspend fun getCurrencyList(): Currency {
        return try {
            val response = currencyApi.getListCurrency()
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
            Currency(ERROR_STRING, listOf())
        }
    }

    companion object {
        private const val ERROR_STRING = ""
    }

}