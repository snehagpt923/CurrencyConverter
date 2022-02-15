package com.example.currencyconverter.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager private constructor(context: Context) {
    private val mPref: SharedPreferences =
        context.getSharedPreferences("com.example.currencyconverter", Context.MODE_PRIVATE)

    fun putLong(key: String?, value: Long?) {
        mPref.edit().putLong(key, value!!).apply()
    }

    fun getLong(key: String?): Long {
        return mPref.getLong(key, 0)
    }

    companion object {
        @get:Synchronized
        var instance: SharedPrefsManager? = null
            private set

        @Synchronized
        fun initializeInstance(context: Context) {
            if (instance == null) {
                instance = SharedPrefsManager(context)
            }
        }

        const val API_CALL_TIME = "API_CALL_TIME"
    }
}