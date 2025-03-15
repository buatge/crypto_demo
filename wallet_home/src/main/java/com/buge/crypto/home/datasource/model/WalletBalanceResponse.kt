package com.buge.crypto.home.datasource.model

import com.google.gson.annotations.SerializedName

data class WalletBalanceResponse(
    @SerializedName("wallet")
    val wallet: List<BalanceInfo>? = null,
    @SerializedName("warning")
    val warning: String? = null,
    @SerializedName("ok")
    val ok: Boolean? = null
)

data class BalanceInfo(
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("amount")
    val amount: String? = null
)