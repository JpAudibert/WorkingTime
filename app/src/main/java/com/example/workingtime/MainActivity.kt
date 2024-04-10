package com.example.workingtime

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {
    private lateinit var timeToCalculate: EditText
    private lateinit var calculateShiftButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        startComponents()
        calculateShiftButton.setOnClickListener {
            calculateShift()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startComponents() {
        timeToCalculate = findViewById(R.id.timeToCalculate)
        calculateShiftButton = findViewById(R.id.calculateShiftBtn)
    }

    private fun calculateShift() {
        val intent = Intent(this, ResultActivity::class.java)

        val timeToCalculateShift = timeToCalculate.text.toString()
        val areFieldsValid = validateFields(timeToCalculateShift)

        if (areFieldsValid) {
            intent.putExtra("time", timeToCalculateShift)
        }
        startActivity(intent)
    }

    private fun validateFields(timeToValidate: String): Boolean {
        if (timeToValidate.isEmpty()) {
            return false
        }

        if (timeToValidate == "") {
            return false
        }
        return true
    }
}