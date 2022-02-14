package com.example.currencyconverter.cache

import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.CurrencyConverterCacheEntity

interface CurrencyConverterDaoService {

    suspend fun insert(CurrencyConverterEntity: CurrencyConverterCacheEntity): Long

    suspend fun get(): List<CurrencyConverterCacheEntity>
}