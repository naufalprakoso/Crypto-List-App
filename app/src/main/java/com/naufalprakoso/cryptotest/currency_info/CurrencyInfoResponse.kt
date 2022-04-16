package com.naufalprakoso.cryptotest.currency_info

import com.google.gson.annotations.SerializedName

class CurrencyResponse(
    @SerializedName("data")
    val coins: List<CurrencyInfoResponse>
)

class CurrencyInfoResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String
)