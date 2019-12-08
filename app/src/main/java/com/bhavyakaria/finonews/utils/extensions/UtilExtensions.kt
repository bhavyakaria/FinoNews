package com.bhavyakaria.finonews.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun removeSourceFromHeadline(text: String): String {
    val pattern = Regex("-[^-]*\$")
    return pattern.replace(text, "")
}

fun parseServerDate(date: String): Date? {
    val DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN)
    dateFormat.timeZone = TimeZone.getTimeZone("IST")
    val reqDateFormat = SimpleDateFormat("dd-MM-yyyy")
    return 
}