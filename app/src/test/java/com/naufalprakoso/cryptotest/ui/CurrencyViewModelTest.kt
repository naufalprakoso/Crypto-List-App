package com.naufalprakoso.cryptotest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.naufalprakoso.cryptotest.currency_info.GetAllDataUseCase
import com.naufalprakoso.cryptotest.currency_info.GetSortedDataUseCase
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CurrencyViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CurrencyViewModel

    @Mock
    private lateinit var getAllDataUseCase: GetAllDataUseCase
    @Mock
    private lateinit var getSortedDataUseCase: GetSortedDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModel = CurrencyViewModel(getAllDataUseCase, getSortedDataUseCase)
    }

    @Test
    fun `loadData, should return match values`() {
        val data = createCurrencyEntity()
        val dataFlow = flow { emit(data) }
        whenever(getAllDataUseCase()).thenReturn(dataFlow)
        viewModel.loadData()
        verify(getAllDataUseCase).invoke()

        assertEquals(data[0].symbol, viewModel.currenciesData.value!![0].symbol)
        assertEquals(data[1].symbol, viewModel.currenciesData.value!![1].symbol)
    }

    @Test
    fun `sortData, should return match values`() {
        val data = createCurrencyEntity()
        val dataFlow = flow { emit(data) }
        whenever(getSortedDataUseCase(any(), any())).thenReturn(dataFlow)
        viewModel.sortData(CurrencyInfoEntity.SortType.NAME, CurrencyInfoEntity.SortOrder.DESCENDING)
        verify(getSortedDataUseCase).invoke(any(), any())

        assertEquals(data[0].symbol, viewModel.currenciesData.value!![0].symbol)
        assertEquals(data[1].symbol, viewModel.currenciesData.value!![1].symbol)
    }

    private fun createCurrencyEntity(): List<CurrencyInfoEntity> {
        return listOf(
            CurrencyInfoEntity("BTC", "Bitcoin", "BTC"),
            CurrencyInfoEntity("AXS", "Axie Infinity", "AXS")
        )
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(
            getAllDataUseCase,
            getSortedDataUseCase
        )
    }
}