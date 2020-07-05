package com.kursivee.graphql.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kursivee.graphql.R
import com.kursivee.graphql.auth.presentation.LoginFragment
import com.kursivee.graphql.di.Scope
import com.kursivee.graphql.ui.main.MainFragment
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
