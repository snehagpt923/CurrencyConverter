package com.example.currencyconverter.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    @Expose
    var code: Int? = null,

    @SerializedName("info")
    @Expose
    var info: String? = null
)