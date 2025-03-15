package com.buge.crypto.home.datasource.model

import com.google.gson.annotations.SerializedName

data class SymbolListResponse(
    @SerializedName("currencies")
    val currencies: List<SymbolInfo>? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("ok")
    val ok: Boolean? = null
)

data class SymbolInfo(
    @SerializedName("coin_id")
    val coinId: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("token_decimal")
    val tokenDecimal: Int? = null,
    @SerializedName("colorful_image_url")
    val colorfulImageUrl: String? = null
)