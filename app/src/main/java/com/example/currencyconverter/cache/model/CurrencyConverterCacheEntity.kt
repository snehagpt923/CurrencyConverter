package com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrencyRate")
data class CurrencyConverterCacheEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "currencyName")
    var currencyName: String,

    @ColumnInfo(name = "currencyRate")
    var currencyRate: Double?
)



