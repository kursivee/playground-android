package com.kursivee.graphql

import android.app.Application
import com.kursivee.graphql.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}