package com.naufalprakoso.cryptotest.currency_info

import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity

class CurrencyMapper {

    fun convertListResponseToEntity(responses: List<CurrencyInfoResponse>): List<CurrencyInfoEntity> {
        return responses.map { convertResponseToEntity(it) }
    }

    private fun convertResponseToEntity(response: CurrencyInfoResponse): CurrencyInfoEntity {
        return CurrencyInfoEntity(
            response.id,
            response.name,
            response.symbol
        )
    }
}