package com.naufalprakoso.cryptotest.currency_info

import com.naufalprakoso.cryptotest.database.dao.CurrencyInfoDao
import com.naufalprakoso.cryptotest.database.entities.CurrencyInfoEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class CurrencyInfoRepositoryImplTest {

    private lateinit var repository: CurrencyInfoRepositoryImpl
    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var currencyService: CurrencyService
    @Mock
    private lateinit var currencyInfoDao: CurrencyInfoDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CurrencyInfoRepositoryImpl(currencyService, currencyInfoDao)
    }

    @Test
    fun `getAllData, when response is success and body not null, should return match values`() {
        dispatcher.runBlockingTest {
            val dataResponse = createCurrencyResponses()
            val response = Response.success(dataResponse)
            val expected = createCurrencyEntity()
            whenever(currencyService.getCoins()).thenReturn(response)
            whenever(currencyInfoDao.getAllData()).thenReturn(expected)

            val actual = repository.getAllData()

            verify(currencyService).getCoins()
            verify(currencyInfoDao).insert(any())
            verify(currencyInfoDao).getAllData()

            assertEquals(expected[0].name, actual[0].name)
            assertEquals(expected[0].id, actual[0].id)
            assertEquals(expected[0].symbol, actual[0].symbol)
        }
    }

    @Test
    fun `getAllData, when response is null, should return from local db`() {
        dispatcher.runBlockingTest {
            val expected = createCurrencyEntity()
            whenever(currencyService.getCoins()).thenReturn(null)
            whenever(currencyInfoDao.getAllData()).thenReturn(expected)

            val actual = repository.getAllData()

            verify(currencyService).getCoins()
            verify(currencyInfoDao, never()).insert(any())
            verify(currencyInfoDao).getAllData()

            assertEquals(expected[0].name, actual[0].name)
            assertEquals(expected[0].id, actual[0].id)
            assertEquals(expected[0].symbol, actual[0].symbol)
        }
    }

    private fun createCurrencyResponses(): CurrencyResponse {
        return CurrencyResponse(
            listOf(
                CurrencyInfoResponse("BTC", "Bitcoin", "BTC"),
                CurrencyInfoResponse("AXS", "Axie Infinity", "AXS")
            )
        )
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
            currencyService,
            currencyInfoDao
        )
    }
}
