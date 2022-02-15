package com.example.currencyconverter.di

import com.example.currencyconverter.domain.data.cache.CacheDataSource
import com.example.currencyconverter.domain.data.network.NetworkDataSource
import com.example.currencyconverter.repository.MainRepository
import com.example.currencyconverter.utils.ResourcesProvider
import com.example.currencyconverter.utils.SharedPrefsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource,
        resourcesProvider: ResourcesProvider,
        sharedPrefsManager: SharedPrefsManager
    ): MainRepository {
        return MainRepository(
            cacheDataSource,
            networkDataSource,
            resourcesProvider,
            sharedPrefsManager
        )
    }
}














