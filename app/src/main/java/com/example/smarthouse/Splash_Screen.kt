package com.example.smarthouse

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Splash_Screen : AppCompatActivity() {
    private val splash_duraction: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFS", 0)
        val savedPin = sharedPreferences.getString("pin", "")

        Handler(Looper.getMainLooper()).postDelayed({
            if (savedPin.isNullOrEmpty()) {
                // Перейти на экран создания ПИН-кода
                val intent = Intent(this, CreatePin::class.java)
                startActivity(intent)
            } else {
                // Перейти на экран ввода ПИН-кода
                val intent = Intent(this, EnterPin::class.java)
                startActivity(intent)
            }
            finish()
        }, splash_duraction)

        
    }

}