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
class GetAllDataUseCaseImplTest {

    private lateinit var useCase: GetAllDataUseCaseImpl
    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var currencyInfoRepository: CurrencyInfoRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetAllDataUseCaseImpl(currencyInfoRepository, dispatcher)
    }

    @Test
    fun `invoke, should return match values`() {
        dispatcher.runBlockingTest {
            val expected = createCurrencyEntity()
            whenever(currencyInfoRepository.getAllData()).thenReturn(expected)

            useCase().collect { actual ->
                assertEquals(expected[0].id, actual[0].id)
                assertEquals(expected[1].id, actual[1].id)
            }

            verify(currencyInfoRepository).getAllData()
        }
    }

    private fun createCurrencyEntity(): List<CurrencyInfoEntity> {
        return listOf(
            CurrencyInfoEntity("BTC", "Bitcoin", "BTC"),
            CurrencyInfoEntity("AXS", "Axie Infinity", "AXS")
        )
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(currencyInfoRepository)
    }
}