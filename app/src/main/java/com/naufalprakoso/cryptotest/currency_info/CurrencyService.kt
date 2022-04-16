package com.naufalprakoso.cryptotest.currency_info

import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {
    @GET("assets")
    suspend fun getCoins(): Response<CurrencyResponse>
}