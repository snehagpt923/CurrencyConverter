package com.example.currencyconverter.di

import android.app.Application
import com.example.currencyconverter.utils.ResourcesProvider
import com.example.currencyconverter.utils.ResourcesProviderImpl
import com.example.currencyconverter.utils.SharedPrefsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideResourcesManager(application: Application): ResourcesProvider =
        ResourcesProviderImpl(application)

    @Singleton
    @Provides
    fun provideSharedPrefsManager(): SharedPrefsManager =
        SharedPrefsManager.instance
}
