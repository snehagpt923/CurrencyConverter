package com.example.currencyconverter.di

import com.example.currencyconverter.domain.data.cache.CacheDataSource
import com.example.currencyconverter.domain.data.network.NetworkDataSource
import com.example.currencyconverter.repository.MainRepository
import com.example.currencyconverter.utils.ResourcesProvider
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
        resourcesProvider: ResourcesProvider
    ): MainRepository {
        return MainRepository(cacheDataSource, networkDataSource, resourcesProvider)
    }
}














