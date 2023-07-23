package com.levp.immersivedotastats.utils

import android.os.Build
import java.time.Instant
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.seconds

fun formatDateTime(epochSeconds: Long): String {
    var dateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochSecond(epochSeconds))
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    dateTime = dateTime.replace(Regex("[TZ]"), " ")
    return dateTime
}

fun formatDuration(seconds: Int): String {
    return seconds.seconds.toComponents { h, m, s, _ -> if (h > 0) "$h:$m:$s" else "$m:$s" }
}

fun formatHeroImageFile(heroId: String): String{
    return "${Constants.HeroImageFilePrefix}${heroId}.jpg"
}