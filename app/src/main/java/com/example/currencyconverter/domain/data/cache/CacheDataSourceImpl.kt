package com.example.currencyconverter.domain.data.cache

import com.example.currencyconverter.cache.CurrencyConverterDaoService
import com.example.currencyconverter.cache.mappers.CacheMapper
import com.example.currencyconverter.domain.data.cache.CacheDataSource
import com.example.currencyconverter.domain.model.CurrencyRateModel

class CacheDataSourceImpl
constructor(
    private val currencyConverterDaoService: CurrencyConverterDaoService,
    private val cacheMapper: CacheMapper
) : CacheDataSource {

    override suspend fun insert(currencyRateModel: CurrencyRateModel): Long {
        return currencyConverterDaoService.insert(cacheMapper.mapToEntity(currencyRateModel))
    }

    override suspend fun insertList(currencyRateModelList: List<CurrencyRateModel>) {
        for (currencyRate in currencyRateModelList) {
            currencyConverterDaoService.insert(cacheMapper.mapToEntity(currencyRate))
        }
    }

    override suspend fun getRates(): List<CurrencyRateModel> {
        return cacheMapper.mapFromEntityList(currencyConverterDaoService.get())
    }

}
