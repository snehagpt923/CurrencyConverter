package com.example.currencyconverter.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.CurrencyConverterCacheEntity

@Dao
interface CurrencyConverterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CurrencyConverterCacheEntity): Long

    @Query("SELECT * FROM CurrencyRate")
    suspend fun get(): List<CurrencyConverterCacheEntity>
}