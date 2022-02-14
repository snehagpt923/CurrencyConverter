package com.example.currencyconverter.network.mapper

import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() :
    EntityMapper<Map<String, Double?>?, List<CurrencyRateModel>> {

    override fun mapFromEntity(entity: Map<String, Double?>?): List<CurrencyRateModel> {
        val list = mutableListOf<CurrencyRateModel>()
        entity?.forEach { quote ->
            list.add(CurrencyRateModel(currencyName = quote.key, currencyRate = quote.value))
        }
        return list
    }

    override fun mapToEntity(domainModel: List<CurrencyRateModel>): Map<String, Double?> {
        val map = mutableMapOf<String, Double?>()
        domainModel.forEach {
            map[it.currencyName] = it.currencyRate
        }
        return map
    }
}