package com.example.currencyconverter.domain.data.network

import com.example.currencyconverter.domain.model.CurrencyRateModel

interface NetworkDataSource {

    suspend fun getCurrencyRates(): List<CurrencyRateModel>
}