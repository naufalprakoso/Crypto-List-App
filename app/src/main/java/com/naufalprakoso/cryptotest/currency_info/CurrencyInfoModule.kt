package com.naufalprakoso.cryptotest.currency_info

import com.naufalprakoso.cryptotest.database.dao.CurrencyInfoDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
class CurrencyInfoModule {

    @Provides
    fun providesCurrencyService(retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }

    @Provides
    fun providesCurrencyInfoRepository(
        currencyService: CurrencyService,
        currencyInfoDao: CurrencyInfoDao
    ): CurrencyInfoRepository {
        return CurrencyInfoRepositoryImpl(currencyService, currencyInfoDao)
    }

    @Provides
    fun providesGetAllDataUseCase(
        currencyInfoRepository: CurrencyInfoRepository
    ) : GetAllDataUseCase {
        return GetAllDataUseCaseImpl(currencyInfoRepository, Dispatchers.IO)
    }

    @Provides
    fun providesGetSortedDataUseCase(
        currencyInfoRepository: CurrencyInfoRepository
    ) : GetSortedDataUseCase {
        return GetSortedDataUseCaseImpl(currencyInfoRepository, Dispatchers.IO)
    }
}
