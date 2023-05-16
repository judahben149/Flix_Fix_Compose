package com.judahben149.flixfixx.utils

import android.icu.text.SimpleDateFormat
import java.util.Locale

object Extensions {

    fun String.parseFriendlyDate(): String {

        if (this != null) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(this)

            val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
            return outputFormat.format(date!!)
        } else {
            return "none"
        }
    }

    fun Int.toRunTimeString(): String {
        var hours = 0
        var minutes = 0

        return if (this >= 60) {
            hours = this / 60
            minutes = this % 60
            "${hours.toString()}h, ${minutes.toString()}m"
        }
        else {
            minutes = this
            "${minutes.toString()}m"
        }

    }
}