package com.kursivee.graphql.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kursivee.graphql.R
import com.kursivee.graphql.ui.main.MainFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val vm: SessionViewModel by viewModel()

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
