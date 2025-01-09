package com.soquipment.presentation.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

sealed interface TimeUtil {
    fun get(): String

    data object Current : TimeUtil {
        override fun get(): String {
            val calendar = Calendar.getInstance().apply {
                time = Date()
            }
            return dateFormat.format(calendar.time)
        }
    }

    data class Relative(val diff: Int) : TimeUtil {
        override fun get(): String {
            val calendar = Calendar.getInstance().apply {
                time = Date()
                add(Calendar.HOUR_OF_DAY, diff)
            }
            return dateFormat.format(calendar.time)
        }

    }

    companion object {
        private const val PATTERN = "HH:mm"
        private val dateFormat = SimpleDateFormat(PATTERN, Locale.KOREA)
    }
}