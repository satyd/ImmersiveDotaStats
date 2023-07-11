package com.levp.immersivedotastats.utils

import android.os.Build
import java.time.Instant
import java.time.format.DateTimeFormatter

fun formatDateTime(epochSeconds: Long): String {
    var dateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochSecond(epochSeconds))
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    dateTime = dateTime.replace(Regex("[TZ]")," ")
    return dateTime
}