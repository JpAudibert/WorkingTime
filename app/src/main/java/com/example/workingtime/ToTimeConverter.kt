package com.example.workingtime

import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Time
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ToTimeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToLocalTime(localDateTime: LocalDateTime): Time {
        val hour = localDateTime.hour
        val minute = localDateTime.minute
        val second = localDateTime.second

        return Time.valueOf("$hour:$minute:$second")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToTime(timeInString: String) : Time {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val localTime = LocalTime.parse(timeInString, formatter)
        val localDate = LocalDate.now()

        val localDateTime = localTime.atDate(localDate)

        val hour = localDateTime.hour
        val minute = localDateTime.minute
        val second = localDateTime.second

        return Time.valueOf("$hour:$minute:$second")
    }
}
