package com.kursivee.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kursivee.mvi.home.presentation.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
                HomeFragment()
            )
            .commit()
    }
}