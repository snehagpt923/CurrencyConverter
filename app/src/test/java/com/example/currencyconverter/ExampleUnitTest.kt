package com.example.currencyconverter

import com.example.currencyconverter.domain.data.cache.CacheDataSource
import com.example.currencyconverter.domain.data.network.NetworkDataSource
import com.example.currencyconverter.repository.MainRepository
import com.example.currencyconverter.utils.ResourcesProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Mock
    lateinit var mockedCacheDataSource: CacheDataSource

    @Mock
    lateinit var mockedNetworkDataSource: NetworkDataSource

    @Mock
    lateinit var resourcesProvider: ResourcesProvider

    private lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainRepository =
            MainRepository(
                mockedCacheDataSource,
                mockedNetworkDataSource,
                resourcesProvider,
                null
            )
    }

    @Test
    fun shouldCallAPI_isFalse() {
        assertFalse(mainRepository.shouldCallAPI(30 * 60 * 1000))
    }

    @Test
    fun shouldCallAPI_isTrue() {
        assertTrue(mainRepository.shouldCallAPI(31 * 60 * 1000))
    }

    @Test
    fun addition_isCorrect() {
        runBlocking {
            mainRepository.addition().collect {
                assertEquals(it, 2 + 2)
            }
        }
    }
}