package com.example.currencyconverter.utils

import android.content.Context
import androidx.annotation.StringRes

interface ResourcesProvider {
    fun getString(@StringRes resId: Int): String
}

class ResourcesProviderImpl(private val appContext: Context) : ResourcesProvider {
    override fun getString(@StringRes resId: Int): String =
        if (resId == 0) "" else appContext.getString(resId)
}

