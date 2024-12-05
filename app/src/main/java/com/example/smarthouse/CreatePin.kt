package com.example.smarthouse

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast

class CreatePin : AppCompatActivity() {
    private val enteredPin = StringBuilder()
    private lateinit var gridLayout: GridLayout
    private lateinit var pinDots: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)

        gridLayout = findViewById(R.id.createPinGrid)
        pinDots = listOf(
            findViewById(R.id.pinDot1),
            findViewById(R.id.pinDot2),
            findViewById(R.id.pinDot3),
            findViewById(R.id.pinDot4)
        )

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener {
                enteredPin.append(button.text)
                updatePinDots()
                if (enteredPin.length == 4) {
                    savePin(enteredPin.toString())
                }
            }
        }
    }

    private fun updatePinDots() {
        for (i in pinDots.indices) {
            pinDots[i].setBackgroundResource(
                if (i < enteredPin.length) R.drawable.pin_dot_filled else R.drawable.pin_dot_empty
            )
        }
    }

    private fun savePin(pin: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFS", 0)
        sharedPreferences.edit().putString("pin", pin).apply()

        Toast.makeText(this, "ПИН-код сохранен!", Toast.LENGTH_SHORT).show()

        // Перейти на экран ввода ПИН-кода после сохранения
        val intent = Intent(this, EnterPin::class.java)
        startActivity(intent)
        finish()
    }
}