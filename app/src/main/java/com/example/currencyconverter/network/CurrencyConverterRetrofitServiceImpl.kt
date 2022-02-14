package com.codingwithmitch.daggerhiltplayground.framework.datasource.network

import com.example.currencyconverter.network.retrofit.ConversionRetrofit
import com.example.currencyconverter.network.model.CurrencyConverterNetworkEntity

class CurrencyConverterRetrofitServiceImpl
constructor(
    private val conversionRetrofit: ConversionRetrofit
) : CurrencyConverterRetrofitService {

    override suspend fun getRates(accessKey: String): CurrencyConverterNetworkEntity {
        return conversionRetrofit.getCurrencyRates(accessKey)
    }
}