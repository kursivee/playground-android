package com.kursivee.learnbr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        broadcast()
    }

    private fun broadcast() {
        Intent().also {
            it.action = "com.kursivee.TEST"
            it.putExtra("data", "Hello")
            sendBroadcast(it)
        }
    }

}