package com.example.workingtime

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var timeToCalculate: TextInputEditText
    private lateinit var idealShiftDisplay: TextView
    private lateinit var maxShiftDisplay: TextView
    private lateinit var smallExtraDisplay: TextView
    private lateinit var largeExtraDisplay: TextView
    private lateinit var calculateShiftButton: Button
    private val toTimeConverter = ToTimeConverter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        startComponents()
        calculateShiftButton.setOnClickListener() {
            calculateShift()
        }
        testingTest()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startComponents() {
        timeToCalculate = findViewById(R.id.timeToCalculate)
        idealShiftDisplay = findViewById(R.id.idealShiftDisplay)
        maxShiftDisplay = findViewById(R.id.maxShiftDisplay)
        smallExtraDisplay = findViewById(R.id.smallExtraDisplay)
        largeExtraDisplay = findViewById(R.id.largeExtraDisplay)
        calculateShiftButton = findViewById(R.id.calculateShiftBtn)
    }

    private fun calculateShift() {
        val timeToCalculateShift = timeToCalculate.text.toString()
        val areFieldsValid = validateFields(timeToCalculateShift)

        if (areFieldsValid) {
            val currentTime = toTimeConverter.convertToTime(timeToCalculateShift)
            val shiftCalculator = ShiftCalculator(currentTime)

            idealShiftDisplay.text = shiftCalculator.calculateShift().toString()
            maxShiftDisplay.text = shiftCalculator.calculateMaxShift().toString()
            smallExtraDisplay.text =
                shiftCalculator.calculateExtraHours(ExtraHourType.LARGE).toString()
            largeExtraDisplay.text =
                shiftCalculator.calculateExtraHours(ExtraHourType.SMALL).toString()
        }
    }

    private fun validateFields(timeToValidate: String): Boolean {
        if (timeToValidate.isEmpty()) {
            return false
        }

        if (timeToValidate != "") {
            return false
        }
        return true
    }


    private fun testingTest() {
        val timeConverter = ToTimeConverter()
        val current = LocalDateTime.of(2024, 3, 31, 7, 0, 0)
        val currentTime = timeConverter.convertToLocalTime(current)
        val shiftCalculator = ShiftCalculator(currentTime)

        val shift = shiftCalculator.calculateShift()
        val maxShift = shiftCalculator.calculateMaxShift()
        val bigExtra = shiftCalculator.calculateExtraHours(ExtraHourType.LARGE)
        val smallExtra = shiftCalculator.calculateExtraHours(ExtraHourType.SMALL)

    }
}