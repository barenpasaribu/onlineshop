package com.barenpasaribu.tokoonline.helper

import java.text.NumberFormat
import java.util.*

class Utils {
    companion object {
        fun rupiah(number: Double): String {
            val localeID = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            return numberFormat.format(number).toString()
        }
    }

}