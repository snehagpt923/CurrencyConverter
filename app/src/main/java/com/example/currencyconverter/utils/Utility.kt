package com.example.currencyconverter.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Utility {

    const val BASE_URL = "http://api.currencylayer.com/"
    const val ACCESS_KEY = "8cf7ec8698b46e4cdd0ec0b3e29b5587"
    const val NUM_COLUMN = 3

    fun hideKeyboard(view: View?) {
        view?.let {
            val inputMethodManager =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}