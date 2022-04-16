package com.naufalprakoso.cryptotest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naufalprakoso.cryptotest.currency_info.GetAllDataUseCase
import com.naufalprakoso.cryptotest.currency_info.GetSortedDataUseCase

class CurrencyViewModelFactory(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val getSortedDataUseCase: GetSortedDataUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyViewModel(getAllDataUseCase, getSortedDataUseCase) as T
    }
}
