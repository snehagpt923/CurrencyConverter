package com.example.currencyconverter.network.retrofit

import com.example.currencyconverter.network.model.CurrencyConverterNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ConversionRetrofit {

    @GET("live")
    suspend fun getCurrencyRates(@Query("access_key") accessKey: String): CurrencyConverterNetworkEntity
}