package com.example.currencyconverter.network.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CurrencyConverterNetworkEntity (
    @SerializedName("success")
    @Expose
    var success: Boolean? = null,

    @SerializedName("terms")
    @Expose
    var terms: String? = null,

    @SerializedName("privacy")
    @Expose
    var privacy: String? = null,

    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null,

    @SerializedName("source")
    @Expose
    var source: String? = null,

    @SerializedName("quotes")
    @Expose
    var quotes: Map<String, Double?>? = null,

    @SerializedName("error")
    @Expose
    var error: Error? = null
)