package com.kursivee

import android.app.Application
import android.content.Intent.ACTION_POWER_CONNECTED
import android.content.Intent.ACTION_POWER_DISCONNECTED
import android.content.IntentFilter
import android.net.wifi.WifiManager
import com.kursivee.learnbr.PowerConnectedReceiver

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val intentFilter = IntentFilter().apply {
            addAction(ACTION_POWER_CONNECTED)
            addAction(ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(PowerConnectedReceiver(), intentFilter)
    }
}