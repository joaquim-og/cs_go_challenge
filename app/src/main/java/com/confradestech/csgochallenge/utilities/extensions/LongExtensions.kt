package com.confradestech.csgochallenge.utilities.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Date pretty formatted from Long value
 *  @return String formatted accord to time Ex.: "Today, 12:00", "May 31, 12:00", "May 31 2022, 12:00"
 **/
fun Long.toPrettyDate(): String {
    val nowTime = Calendar.getInstance()
    val neededTime = Calendar.getInstance()
    neededTime.timeInMillis = this

    return if (neededTime[Calendar.YEAR] == nowTime[Calendar.YEAR]) {
        if (neededTime[Calendar.MONTH] == nowTime[Calendar.MONTH]) {
            when {
                neededTime[Calendar.DATE] - nowTime[Calendar.DATE] == 1 -> {
                    //tomorrow time
                    "Amanhã, ${
                        SimpleDateFormat(
                            DATE_TIME_FORMAT_HH_MM,
                            Locale.getDefault()
                        ).format(
                            Date(this)
                        )
                    }"
                }

                nowTime[Calendar.DATE] == neededTime[Calendar.DATE] -> {
                    //today time
                    "Hoje, ${
                        SimpleDateFormat(DATE_TIME_FORMAT_HH_MM, Locale.getDefault()).format(
                            Date(this)
                        )
                    }"
                }

                nowTime[Calendar.DATE] - neededTime[Calendar.DATE] == 1 -> {
                    //yesterday time
                    "Ontem, ${
                        SimpleDateFormat(
                            DATE_TIME_FORMAT_HH_MM,
                            Locale.getDefault()
                        ).format(
                            Date(this)
                        )
                    }"
                }

                else -> {
                    //date in this year "May 31, 12:00"
                    SimpleDateFormat(DATE_TIME_FORMAT_M_DD_HH_MM, Locale.getDefault()).format(
                        Date(this)
                    )
                }
            }
        } else {
            //date in this year "May 31, 12:00"
            SimpleDateFormat(DATE_TIME_FORMAT_M_DD_HH_MM, Locale.getDefault()).format(
                Date(this)
            )
        }
    } else {
        ///date in different year "May 31 2022, 12:00"
        SimpleDateFormat(DATE_TIME_FORMAT_M_DD_YYYY_HH_MM, Locale.getDefault()).format(
            Date(this)
        )
    }
}