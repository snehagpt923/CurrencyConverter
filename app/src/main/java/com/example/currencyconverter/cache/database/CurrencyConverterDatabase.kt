package com.example.currencyconverter.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.CurrencyConverterCacheEntity
import com.example.currencyconverter.cache.database.CurrencyConverterDao

@Database(entities = [CurrencyConverterCacheEntity::class], version = 1)
abstract class CurrencyConverterDatabase : RoomDatabase() {

    abstract fun currencyRateDao(): CurrencyConverterDao

    companion object {
        const val DATABASE_NAME: String = "CurrencyConverter_db"
    }
}