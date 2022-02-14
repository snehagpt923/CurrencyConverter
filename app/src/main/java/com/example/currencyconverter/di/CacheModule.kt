package com.example.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.domain.data.cache.CacheDataSource
import com.example.currencyconverter.domain.data.cache.CacheDataSourceImpl
import com.example.currencyconverter.cache.CurrencyConverterDaoService
import com.example.currencyconverter.cache.CurrencyConverterDaoServiceImpl
import com.example.currencyconverter.cache.database.CurrencyConverterDao
import com.example.currencyconverter.cache.database.CurrencyConverterDatabase
import com.example.currencyconverter.cache.mappers.CacheMapper
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.CurrencyConverterCacheEntity
import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.utils.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<CurrencyConverterCacheEntity, CurrencyRateModel> {
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideCurrencyConverterDb(@ApplicationContext context: Context): CurrencyConverterDatabase {
        return Room
            .databaseBuilder(
                context,
                CurrencyConverterDatabase::class.java,
                CurrencyConverterDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyRateDAO(database: CurrencyConverterDatabase): CurrencyConverterDao {
        return database.currencyRateDao()
    }

    @Singleton
    @Provides
    fun provideCurrencyConverterDaoService(
        dao: CurrencyConverterDao
    ): CurrencyConverterDaoService {
        return CurrencyConverterDaoServiceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        daoService: CurrencyConverterDaoService,
        cacheMapper: CacheMapper
    ): CacheDataSource {
        return CacheDataSourceImpl(daoService, cacheMapper)
    }
}

























