package com.example.workingtime

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    private lateinit var idealShiftDisplay: TextView
    private lateinit var maxShiftDisplay: TextView
    private lateinit var smallExtraDisplay: TextView
    private lateinit var largeExtraDisplay: TextView
    private lateinit var clearButton : Button
    private val toTimeConverter = ToTimeConverter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        startComponents()

        val bundle = intent.extras
        val timeToCalculateShift = bundle?.getString("time") ?: return
        val currentTime = toTimeConverter.convertToTime(timeToCalculateShift)
        val shiftCalculator = ShiftCalculator(currentTime)

        idealShiftDisplay.text = shiftCalculator.calculateShift().toString()
        maxShiftDisplay.text = shiftCalculator.calculateMaxShift().toString()
        smallExtraDisplay.text =
            shiftCalculator.calculateExtraHours(ExtraHourType.SMALL).toString()
        largeExtraDisplay.text =
            shiftCalculator.calculateExtraHours(ExtraHourType.LARGE).toString()

        clearButton.setOnClickListener{
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startComponents() {
        idealShiftDisplay = findViewById(R.id.idealShiftDisplay)
        maxShiftDisplay = findViewById(R.id.maxShiftDisplay)
        smallExtraDisplay = findViewById(R.id.smallExtraDisplay)
        largeExtraDisplay = findViewById(R.id.largeExtraDisplay)
        clearButton = findViewById(R.id.clearBtn)
    }
}