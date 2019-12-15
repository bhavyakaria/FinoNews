package com.bhavyakaria.finonews.utils.extensions
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun removeSourceFromHeadline(text: String): String {
    val pattern = Regex("-[^-]*\$")
    return pattern.replace(text, "")
}

fun parseServerDate(date: String): String? {
    val dt = DateTime(date)
    val fmt = DateTimeFormat.forPattern("d MMMM, yyyy")
    return dt.toString(fmt)
}