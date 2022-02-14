package com.codingwithmitch.daggerhiltplayground.framework.datasource.network

import com.example.currencyconverter.network.model.CurrencyConverterNetworkEntity

interface CurrencyConverterRetrofitService {

    suspend fun getRates(accessKey: String): CurrencyConverterNetworkEntity
}