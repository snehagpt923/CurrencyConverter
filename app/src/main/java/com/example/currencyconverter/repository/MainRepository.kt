package com.example.currencyconverter.repository

import com.example.currencyconverter.R
import com.example.currencyconverter.domain.data.cache.CacheDataSource
import com.example.currencyconverter.domain.data.network.NetworkDataSource
import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.utils.DataState
import com.example.currencyconverter.utils.ResourcesProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource,
    private val resourcesProvider: ResourcesProvider
) {
    suspend fun getConverterRates(): Flow<DataState<List<CurrencyRateModel>>> = flow {
        emit(DataState.Loading)
        try {
            networkDataSource.getCurrencyRates().takeIf { it.isNotEmpty() }?.let {
                cacheDataSource.insertList(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cacheDataSource.getRates().takeIf { it.isNotEmpty() }?.let {
                emit(DataState.Success(it))
            } ?: run { emit(DataState.Error(Exception(resourcesProvider.getString(R.string.no_currency_rates)))) }
        }
    }
}