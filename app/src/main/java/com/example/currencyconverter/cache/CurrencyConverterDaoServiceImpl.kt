package com.example.currencyconverter.cache

import com.example.currencyconverter.cache.database.CurrencyConverterDao
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.CurrencyConverterCacheEntity

class CurrencyConverterDaoServiceImpl
constructor(
    private val currencyConverterDao: CurrencyConverterDao
) : CurrencyConverterDaoService {

    override suspend fun insert(CurrencyConverterEntity: CurrencyConverterCacheEntity): Long {
        return currencyConverterDao.insert(CurrencyConverterEntity)
    }

    override suspend fun get(): List<CurrencyConverterCacheEntity> {
        return currencyConverterDao.get()
    }
}