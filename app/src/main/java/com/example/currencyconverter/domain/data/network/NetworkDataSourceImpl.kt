package com.example.currencyconverter.domain.data.network

import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.CurrencyConverterRetrofitService
import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.network.mapper.NetworkMapper
import com.example.currencyconverter.utils.Utility.ACCESS_KEY

class NetworkDataSourceImpl
constructor(
    private val retrofitService: CurrencyConverterRetrofitService,
    private val networkMapper: NetworkMapper
): NetworkDataSource {

    override suspend fun getCurrencyRates(): List<CurrencyRateModel> {
        return networkMapper.mapFromEntity(retrofitService.getRates(ACCESS_KEY).quotes)
    }
}