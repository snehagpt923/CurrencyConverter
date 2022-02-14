package com.example.currencyconverter.di

import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.CurrencyConverterRetrofitService
import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.CurrencyConverterRetrofitServiceImpl
import com.example.currencyconverter.domain.data.network.NetworkDataSource
import com.example.currencyconverter.domain.data.network.NetworkDataSourceImpl
import com.example.currencyconverter.network.mapper.NetworkMapper
import com.example.currencyconverter.network.retrofit.ConversionRetrofit
import com.example.currencyconverter.utils.Utility.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Builder {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)
        }
        return Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideCurrencyConverterService(retrofit: Builder): ConversionRetrofit {
        return retrofit
            .build()
            .create(ConversionRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        conversionRetrofit: ConversionRetrofit
    ): CurrencyConverterRetrofitService {
        return CurrencyConverterRetrofitServiceImpl(conversionRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        retrofitService: CurrencyConverterRetrofitService,
        networkMapper: NetworkMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(retrofitService, networkMapper)
    }
}