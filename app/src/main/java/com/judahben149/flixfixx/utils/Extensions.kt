package com.judahben149.flixfixx.utils

import android.icu.text.SimpleDateFormat
import java.text.DecimalFormat
import java.util.Locale
import kotlin.math.abs

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
            "${hours.toString()}h${minutes.toString()}m"
        }
        else {
            minutes = this
            "${minutes.toString()}m"
        }

    }

    fun Double.trimDecimalPlaces(decimalPlaces: Int): Double {
        val pattern = "#.${"0".repeat(decimalPlaces)}"
        val decimalFormat = DecimalFormat(pattern)
        return decimalFormat.format(this).toDouble()
    }

    fun Int.shortenVoteCount(): String {
        if (this < 999) {
            return this.toString()
        }

        val suffix = listOf("k", "M", "B")
        val count = abs(this).toFloat()
        var countDivided = 0

        countDivided = (count/1000).toInt()
        if (countDivided < 1000) {
            return countDivided.toString().plus(suffix.get(0))
        }
        else {
            countDivided = countDivided/1000
            if (countDivided < 1000) {
                return countDivided.toString().plus(suffix.get(1))
            }
            else {
                return countDivided.toString().plus(suffix.get(2))
            }
        }
    }
}