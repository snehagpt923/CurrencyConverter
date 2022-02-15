package com.example.currencyconverter

import android.app.Application
import com.example.currencyconverter.utils.SharedPrefsManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefsManager.initializeInstance(this)
    }
}