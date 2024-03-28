package com.gap.data.network

import com.gap.data.network.dto.CurrencyDto
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {
    @GET("daily_json.js")
    suspend fun getListCurrency(): Response<CurrencyDto>

}
