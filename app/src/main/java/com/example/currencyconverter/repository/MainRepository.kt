package com.example.currencyconverter.repository

import com.example.currencyconverter.R
import com.example.currencyconverter.domain.data.cache.CacheDataSource
import com.example.currencyconverter.domain.data.network.NetworkDataSource
import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.utils.DataState
import com.example.currencyconverter.utils.ResourcesProvider
import com.example.currencyconverter.utils.SharedPrefsManager
import com.example.currencyconverter.utils.SharedPrefsManager.Companion.API_CALL_TIME
import com.example.currencyconverter.utils.Utility.API_CALL_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.System.currentTimeMillis

class MainRepository constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource,
    private val resourcesProvider: ResourcesProvider,
    private val sharedPrefsManager: SharedPrefsManager?
) {
    private var error: Exception? = null

    suspend fun getConverterRates(): Flow<DataState<List<CurrencyRateModel>>> = flow {
        emit(DataState.Loading)
        try {
            if (sharedPrefsManager == null || shouldCallAPI(getApiCallInterval())) {
                networkDataSource.getCurrencyRates().takeIf { it.isNotEmpty() }?.let {
                    sharedPrefsManager?.putLong(API_CALL_TIME, currentTimeMillis())
                    cacheDataSource.insertList(it)
                }
            }
        } catch (e: Exception) {
            error = e
            e.printStackTrace()
        } finally {
            cacheDataSource.getRates().takeIf { it.isNotEmpty() }?.let {
                emit(DataState.Success(it))
            } ?: run {
                val exception =
                    error ?: Exception(resourcesProvider.getString(R.string.no_currency_rates))
                emit(DataState.Error(exception))
            }
        }
    }

    private fun getApiCallInterval(): Long {
        sharedPrefsManager?.let {
            return currentTimeMillis() - it.getLong(API_CALL_TIME)
        }
        return 0
    }

    fun shouldCallAPI(duration: Long) = duration > API_CALL_DURATION

    fun addition() = flow {
        delay(3000)
        emit(4)
    }
}