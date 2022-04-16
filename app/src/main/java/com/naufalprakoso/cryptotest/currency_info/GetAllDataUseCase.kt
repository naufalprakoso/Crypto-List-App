package com.naufalprakoso.cryptotest.currency_info

import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetAllDataUseCase {
    operator fun invoke(): Flow<List<CurrencyInfoEntity>>
}

class GetAllDataUseCaseImpl(
    private val currencyInfoRepository: CurrencyInfoRepository,
    private val dispatcher: CoroutineDispatcher
) : GetAllDataUseCase {

    override fun invoke(): Flow<List<CurrencyInfoEntity>> {
        return flow {
            val result = currencyInfoRepository.getAllData()
            emit(result)
        }.flowOn(dispatcher)
    }

}