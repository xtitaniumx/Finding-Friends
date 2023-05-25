package com.example.peoplefind.presentation.util

import androidx.core.text.isDigitsOnly
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.ChronoUnit

fun convertAgeToIsoDateTime(age: String): String {
    if (!age.isDigitsOnly()) return ""

    val now = LocalDateTime.now()
    val birthDateTime: LocalDateTime = now.minus(age.toLong(), ChronoUnit.YEARS)
    return birthDateTime.atOffset(ZoneOffset.UTC).toString()
}

fun convertIsoDateTimeToAge(isoDateTime: String): Int {
    val birthDateTime = LocalDateTime.parse(isoDateTime)
    val now = LocalDateTime.now()
    return ChronoUnit.YEARS.between(birthDateTime, now).toInt()
}