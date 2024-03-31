package com.example.workingtime

import java.sql.Time

class ShiftCalculator(private val startingTime: Time) {
    fun calculateShift(): Time {
        return addHoursAndMinutes(startingTime, 9, 48)
    }

    fun calculateMaxShift(): Time {
        return addHoursAndMinutes(startingTime, 11)
    }

    fun calculateExtraHours(type: ExtraHourType): Time {
        if(type == ExtraHourType.SMALL)
            return addHoursAndMinutes(startingTime, 10, 3)

        if(type == ExtraHourType.LARGE)
            return addHoursAndMinutes(startingTime, 10, 18)

        return startingTime
    }

    private fun addHoursAndMinutes(time: Time, hours: Int, minutes: Int = 0): Time {
        val milliseconds = time.time + (hours * 3600000) + (minutes * 60000) // Convert hours and minutes to milliseconds
        return Time(milliseconds)
    }
}

