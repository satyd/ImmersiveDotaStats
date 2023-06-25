package com.levp.immersivedotastats.utils

fun shuffleStringLettersWithSpacing(string: String): String {
    var preName = string.lowercase()
    if (preName.length >= 10 && !preName.contains(" ")) {
        preName = preName.plus(" ")
    }
    val memeName = preName.toCharArray()
    memeName.shuffle()
    return memeName.concatToString()
}