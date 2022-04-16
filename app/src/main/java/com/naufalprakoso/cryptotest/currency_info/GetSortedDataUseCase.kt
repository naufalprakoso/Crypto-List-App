package com.naufalprakoso.cryptotest.currency_info

import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetSortedDataUseCase {
    operator fun invoke(
        type: CurrencyInfoEntity.SortType,
        order: CurrencyInfoEntity.SortOrder
    ): Flow<List<CurrencyInfoEntity>>
}

class GetSortedDataUseCaseImpl(
    private val currencyInfoRepository: CurrencyInfoRepository,
    private val dispatcher: CoroutineDispatcher
) : GetSortedDataUseCase {

    override fun invoke(
        type: CurrencyInfoEntity.SortType,
        order: CurrencyInfoEntity.SortOrder
    ): Flow<List<CurrencyInfoEntity>> {
        return flow {
            val data = currencyInfoRepository.getLocalData()
            if (order == CurrencyInfoEntity.SortOrder.ASCENDING) {
                emit(sortByAsc(data, type))
            } else {
                emit(sortByDesc(data, type))
            }
        }.flowOn(dispatcher)
    }

    private fun sortByAsc(
        data: List<CurrencyInfoEntity>,
        type: CurrencyInfoEntity.SortType
    ): List<CurrencyInfoEntity> {
        return data.sortedBy {
            when (type) {
                CurrencyInfoEntity.SortType.ID -> it.id
                CurrencyInfoEntity.SortType.NAME -> it.name
                else -> it.symbol
            }
        }
    }

    private fun sortByDesc(
        data: List<CurrencyInfoEntity>,
        type: CurrencyInfoEntity.SortType
    ): List<CurrencyInfoEntity> {
        return data.sortedByDescending {
            when (type) {
                CurrencyInfoEntity.SortType.ID -> it.id
                CurrencyInfoEntity.SortType.NAME -> it.name
                else -> it.symbol
            }
        }
    }

}
