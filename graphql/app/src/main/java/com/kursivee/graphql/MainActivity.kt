package com.kursivee.graphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kursivee.graphql.home.presentation.HomeFragment
import com.kursivee.graphql.ui.main.MainFragment
import kotlinx.coroutines.*
import org.koin.android.scope.lifecycleScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
