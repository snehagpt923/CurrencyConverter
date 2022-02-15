package com.example.currencyconverter.util

import com.example.currencyconverter.domain.data.network.NetworkDataSource
import com.example.currencyconverter.domain.model.CurrencyRateModel

class MockedNetworkDataSourceImpl() : NetworkDataSource {

    override suspend fun getCurrencyRates(): List<CurrencyRateModel> {
//        return mutableListOf()
        return mutableListOf(CurrencyRateModel("12", 12.33))
    }
}