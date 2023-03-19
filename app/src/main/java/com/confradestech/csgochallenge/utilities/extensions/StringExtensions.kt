package com.confradestech.csgochallenge.utilities.extensions

import java.text.SimpleDateFormat
import java.util.Locale

const val DATE_TIME_FORMAT_HH_MM = "HH:mm"
const val DATE_TIME_FORMAT_M_DD_HH_MM = "MMM dd, HH:mm"
const val DATE_TIME_FORMAT_M_DD_YYYY_HH_MM = "MMMM dd yyyy, HH:mm"
const val DATE_TIME_FORMAT_YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss"

fun String.convertTimestampToPrettyDate(): String? =
    SimpleDateFormat(
        DATE_TIME_FORMAT_YYYY_MM_DD_T_HH_MM_SS,
        Locale.getDefault()
    ).parse(this)?.time?.toPrettyDate()
