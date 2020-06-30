package com.kursivee.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.iid.FirebaseInstanceId
import com.kursivee.mvi.home.presentation.HomeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
                HomeFragment()
            )
            .commit()

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
                println(it.result?.token)
            }
    }
}