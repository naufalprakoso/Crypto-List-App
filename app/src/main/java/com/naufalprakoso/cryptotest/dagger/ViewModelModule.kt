package com.naufalprakoso.cryptotest.dagger

import com.naufalprakoso.cryptotest.currency_info.GetAllDataUseCase
import com.naufalprakoso.cryptotest.currency_info.GetSortedDataUseCase
import com.naufalprakoso.cryptotest.ui.CurrencyViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesCurrencyViewModelFactory(
        getAllDataUseCase: GetAllDataUseCase,
        getSortedDataUseCase: GetSortedDataUseCase
    ) : CurrencyViewModelFactory {
        return CurrencyViewModelFactory(getAllDataUseCase, getSortedDataUseCase)
    }
}