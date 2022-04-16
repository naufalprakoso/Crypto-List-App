package com.naufalprakoso.cryptotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalprakoso.cryptotest.currency_info.GetAllDataUseCase
import com.naufalprakoso.cryptotest.currency_info.GetSortedDataUseCase
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val getSortedDataUseCase: GetSortedDataUseCase
) : ViewModel() {

    private val currenciesEvent = MutableLiveData<List<CurrencyInfoEntity>>()
    val currenciesData = currenciesEvent as LiveData<List<CurrencyInfoEntity>>

    fun loadData() {
        viewModelScope.launch {
            getAllDataUseCase().collect { data ->
                currenciesEvent.postValue(data)
            }
        }
    }

    fun sortData(type: CurrencyInfoEntity.SortType, order: CurrencyInfoEntity.SortOrder) {
        viewModelScope.launch {
            getSortedDataUseCase(type, order).collect { data ->
                currenciesEvent.value = data
            }
        }
    }
}

