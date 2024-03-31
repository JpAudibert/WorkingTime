package com.example.workingtime

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        testingTest()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }

    private fun testingTest() {
        val timeConverter = ToTimeConverter()
        val current = LocalDateTime.of(2024,3,31,7,0,0)
        val currentTime = timeConverter.convertToLocalTime(current)
        val shiftCalculator = ShiftCalculator(currentTime)

        val shift = shiftCalculator.calculateShift()
        val maxShift = shiftCalculator.calculateMaxShift()
        val bigExtra = shiftCalculator.calculateExtraHours(ExtraHourType.LARGE)
        val smallExtra = shiftCalculator.calculateExtraHours(ExtraHourType.SMALL)

    }
}