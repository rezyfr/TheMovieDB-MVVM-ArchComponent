package com.example.base.utils

import android.os.Build
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun Int.toUsd(): String {
    return if (this != 0) {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("USD")

        format.format(this)
    } else {
        "Unkown"
    }
}

fun String.toDateView(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val date = LocalDate.parse(this)
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        date.format(formatter)
    } else {
        this
    }
}