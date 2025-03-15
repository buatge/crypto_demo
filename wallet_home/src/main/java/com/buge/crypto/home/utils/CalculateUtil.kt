package com.buge.crypto.home.utils

import com.buge.crypto.home.datasource.model.BalanceInfo
import com.buge.crypto.home.datasource.model.SymbolInfo
import com.buge.crypto.home.datasource.model.TierInfo
import com.buge.crypto.home.features.model.AssetBalanceData
import java.math.BigDecimal

object CalculateUtil {

    const val DEFAULT_CURRENCY = "USD"
    const val DEFAULT_CURRENCY_SYMBOL = "$"

    fun calculateBalanceList(
        symbolMap: Map<String, SymbolInfo>,
        rateMap: Map<String, TierInfo>,
        balances: List<BalanceInfo>
    ): Pair<String, List<AssetBalanceData>> {
        val balancesResult = mutableListOf<AssetBalanceData>()
        var totalBalance = BigDecimal(0)

        balances.forEach { balanceInfo ->
            val symbolInfo = symbolMap[balanceInfo.currency]
            val rateInfo = rateMap["${balanceInfo.currency}$DEFAULT_CURRENCY"]
            val rateTarget = rateInfo?.rates?.find {
                !it.rate.isNullOrEmpty()
            }

            if (symbolInfo != null && rateInfo != null && !rateTarget?.rate.isNullOrEmpty()) {
                val assetBalance = AssetBalanceData()
                assetBalance.symbol = symbolInfo.symbol
                assetBalance.name = symbolInfo.name
                assetBalance.icon = symbolInfo.colorfulImageUrl
                assetBalance.amount = balanceInfo.amount
                var balanceValue = calculateBalanceValue(
                    balanceInfo.amount ?: "",
                    rateTarget?.rate ?: ""
                )
                balanceValue?.let {
                    totalBalance = totalBalance.plus(it)
                }
                assetBalance.value = balanceValue?.toPlainString()?: ""
                balancesResult.add(assetBalance)
            }
        }
        return Pair(totalBalance.toPlainString(), balancesResult)
    }

    private fun calculateBalanceValue(
        amount: String,
        rate: String
    ): BigDecimal? {
        try {
            val amountBigDecimal = BigDecimal(amount)
            val raeBigDecimal = BigDecimal(rate)
            return amountBigDecimal.multiply(raeBigDecimal)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return null
    }


}