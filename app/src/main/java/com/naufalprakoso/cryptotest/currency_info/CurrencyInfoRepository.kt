package com.naufalprakoso.cryptotest.currency_info

import com.naufalprakoso.cryptotest.database.dao.CurrencyInfoDao
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import java.lang.Exception

interface CurrencyInfoRepository {
    suspend fun getAllData(): List<CurrencyInfoEntity>
    suspend fun getLocalData(): List<CurrencyInfoEntity>
}

class CurrencyInfoRepositoryImpl(
    private val currencyService: CurrencyService,
    private val currencyInfoDao: CurrencyInfoDao
) : CurrencyInfoRepository {

    private val currencyMapper: CurrencyMapper by lazy { CurrencyMapper() }

    override suspend fun getAllData(): List<CurrencyInfoEntity> {
        return try {
            val response = currencyService.getCoins()
            if (response.isSuccessful && response.body() != null) {
                val dataResponse = currencyMapper.convertListResponseToEntity(response.body()!!.coins)
                currencyInfoDao.insert(dataResponse)
                currencyInfoDao.getAllData()
            } else {
                currencyInfoDao.getAllData()
            }
        } catch (e: Exception) {
            currencyInfoDao.getAllData()
        }
    }

    override suspend fun getLocalData(): List<CurrencyInfoEntity> {
        return currencyInfoDao.getAllData()
    }

}
