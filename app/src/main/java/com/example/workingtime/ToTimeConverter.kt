package com.example.workingtime

import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Time
import java.time.LocalDateTime

class ToTimeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToLocalTime(localDateTime: LocalDateTime): Time {
        val hour = localDateTime.hour
        val minute = localDateTime.minute
        val second = localDateTime.second

        return Time.valueOf("$hour:$minute:$second")
    }
}