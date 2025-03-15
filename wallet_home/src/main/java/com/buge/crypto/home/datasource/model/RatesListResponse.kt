package com.buge.crypto.home.datasource.model

import com.google.gson.annotations.SerializedName

data class RatesListResponse(
    @SerializedName("tiers")
    val tiers: List<TierInfo>? = null,
    @SerializedName("warning")
    val warning: String? = null,
    @SerializedName("ok")
    val ok: Boolean? = null
)

data class TierInfo(
    @SerializedName("from_currency")
    val fromCurrency: String? = null,
    @SerializedName("to_currency")
    val toCurrency: String? = null,
    @SerializedName("rates")
    val rates: List<RateInfo>? = null,
    @SerializedName("time_stamp")
    val timeStamp: Long? = null

)

data class RateInfo(
    @SerializedName("amount")
    val amount: String? = null,
    @SerializedName("rate")
    val rate: String? = null
)