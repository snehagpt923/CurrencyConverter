package com.example.currencyconverter.cache.mappers

import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.CurrencyConverterCacheEntity
import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<CurrencyConverterCacheEntity, CurrencyRateModel> {

    override fun mapFromEntity(entity: CurrencyConverterCacheEntity): CurrencyRateModel =
        CurrencyRateModel(entity.currencyName, entity.currencyRate)

    override fun mapToEntity(domainModel: CurrencyRateModel): CurrencyConverterCacheEntity {
        return CurrencyConverterCacheEntity(
            currencyName = domainModel.currencyName,
            currencyRate = domainModel.currencyRate
        )
    }

    fun mapFromEntityList(entities: List<CurrencyConverterCacheEntity>): List<CurrencyRateModel> {
        return entities.map { mapFromEntity(it) }
    }
}











