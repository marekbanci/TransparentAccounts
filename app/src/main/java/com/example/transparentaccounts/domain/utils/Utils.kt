package com.example.transparentaccounts.domain.utils

import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale

object Utils {

    private val DATE_API_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    private val DISPLAY_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    fun formatDateToDisplay(date: String): String {
        val dateTime = LocalDateTime.parse(date, DATE_API_FORMAT)
        return dateTime.format(DISPLAY_FORMAT)
    }

    fun formatCurrency(amount: Double, currencyCode: String, locale: Locale = Locale("cs", "CZ")): String {
        val currency = Currency.getInstance(currencyCode)
        val format = NumberFormat.getCurrencyInstance(locale)
        format.currency = currency
        return format.format(amount)
    }

}