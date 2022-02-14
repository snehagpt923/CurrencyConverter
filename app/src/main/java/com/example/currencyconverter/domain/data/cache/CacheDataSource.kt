package com.example.currencyconverter.domain.data.cache

import com.example.currencyconverter.domain.model.CurrencyRateModel

interface CacheDataSource {

    suspend fun insert(currencyRateModel: CurrencyRateModel): Long

    suspend fun insertList(currencyRateModelList: List<CurrencyRateModel>)

    suspend fun getRates(): List<CurrencyRateModel>
}