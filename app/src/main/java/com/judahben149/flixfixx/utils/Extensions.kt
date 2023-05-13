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
}