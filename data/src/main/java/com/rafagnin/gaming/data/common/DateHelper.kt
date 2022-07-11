package com.rafagnin.gaming.data.common

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    private const val API_DATE_FORMAT = "yyyy-MM-dd"

    fun mapDate(date: String?, newFormat: String): String? {
        if (date == null) return null
        val formatOld = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
        val formatNew = SimpleDateFormat(newFormat, Locale.getDefault())
        val formatted = formatOld.parse(date)
        return formatNew.format(formatted)
    }
}