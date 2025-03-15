package com.buge.crypto.home.utils

import java.math.BigDecimal
import java.math.RoundingMode

object NumberFormatUtil {

    private fun format(
        number: BigDecimal?,
        scale: Int,
        roundingMode: RoundingMode = RoundingMode.DOWN
    ): String {
        if (number == null) {
            return ""
        }
        return number.setScale(scale, roundingMode).stripTrailingZeros().toPlainString()
    }

    fun format(
        number: String,
        scale: Int = 8,
        roundingMode: RoundingMode = RoundingMode.DOWN
    ): String {
        return format(number.toBigDecimalOrNull(), scale, roundingMode)
    }

}