package com.kursivee.graphql

import android.app.Application
import com.kursivee.graphql.di.appModules
import com.kursivee.graphql.di.baseModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@FlowPreview
@ExperimentalCoroutinesApi
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules, baseModules)
        }
    }
}