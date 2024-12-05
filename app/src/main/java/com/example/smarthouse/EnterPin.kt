package com.example.smarthouse

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast

class EnterPin : AppCompatActivity() {
    private val enteredPin = StringBuilder()
    private lateinit var gridLayout: GridLayout
    private lateinit var pinDots: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_pin)

        gridLayout = findViewById(R.id.enterPinGrid)
        pinDots = listOf(
            findViewById(R.id.pinDot1),
            findViewById(R.id.pinDot2),
            findViewById(R.id.pinDot3),
            findViewById(R.id.pinDot4)
        )
        val exitButton: Button = findViewById(R.id.exitButton)

        exitButton.setOnClickListener {
            finishAffinity()
        }

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener {
                enteredPin.append(button.text)
                updatePinDots()
                if (enteredPin.length == 4) {
                    checkPin(enteredPin.toString())
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

    private fun checkPin(pin: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFS", 0)
        val savedPin = sharedPreferences.getString("pin", "")

        if (pin == savedPin) {
            Toast.makeText(this, "ПИН-код верный!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Неверный ПИН-код!", Toast.LENGTH_SHORT).show()
            enteredPin.clear()
            updatePinDots()
        }
    }
}