package com.naufalprakoso.cryptotest.currency_info

import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetSortedDataUseCaseImplTest {

    private lateinit var useCase: GetSortedDataUseCaseImpl
    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var currencyInfoRepository: CurrencyInfoRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetSortedDataUseCaseImpl(currencyInfoRepository, dispatcher)
    }

    @Test
    fun `invoke, when order by Symbol ASC, should return sorted data by Symbol ASC`() {
        dispatcher.runBlockingTest {
            whenever(currencyInfoRepository.getLocalData()).thenReturn(createCurrencyEntity())
            useCase(CurrencyInfoEntity.SortType.SYMBOL, CurrencyInfoEntity.SortOrder.ASCENDING).collect { sortedData ->
                assertEquals("AXS", sortedData[0].symbol)
                assertEquals("BTC", sortedData[1].symbol)
                assertEquals("ETH", sortedData[2].symbol)
                assertEquals("ZIL", sortedData[3].symbol)
            }
            verify(currencyInfoRepository).getLocalData()
        }
    }

    @Test
    fun `invoke, when order by Name DESC, should return sorted data by Name DESC`() {
        dispatcher.runBlockingTest {
            whenever(currencyInfoRepository.getLocalData()).thenReturn(createCurrencyEntity())
            useCase(CurrencyInfoEntity.SortType.NAME, CurrencyInfoEntity.SortOrder.DESCENDING).collect { sortedData ->
                assertEquals("Zilliqa", sortedData[0].name)
                assertEquals("Ethereum", sortedData[1].name)
                assertEquals("Bitcoin", sortedData[2].name)
                assertEquals("Axie Infinity", sortedData[3].name)
            }
            verify(currencyInfoRepository).getLocalData()
        }
    }

    private fun createCurrencyEntity(): List<CurrencyInfoEntity> {
        return listOf(
            CurrencyInfoEntity("BTC", "Bitcoin", "BTC"),
            CurrencyInfoEntity("AXS", "Axie Infinity", "AXS"),
            CurrencyInfoEntity("ZIL", "Zilliqa", "ZIL"),
            CurrencyInfoEntity("ETH", "Ethereum", "ETH")
        )
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(currencyInfoRepository)
    }
}