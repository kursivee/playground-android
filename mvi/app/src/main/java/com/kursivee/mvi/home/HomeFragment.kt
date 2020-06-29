package com.kursivee.mvi.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kursivee.mvi.R
import com.kursivee.mvi.common.ui.BaseFragment
import com.kursivee.mvi.home.event.HomeEvent
import com.kursivee.mvi.home.state.HomeState

class HomeFragment : BaseFragment<HomeState, HomeEvent, HomeViewModel>() {

    private lateinit var tvText: TextView

    override val vm: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvText = requireView().findViewById(R.id.tv_text)
        requireView().findViewById<Button>(R.id.btn_button).init()
        requireView().findViewById<Button>(R.id.btn_toast).initToast()
    }

    private fun Button.init() {
        setOnClickListener {
            vm.process(HomeEvent.UpdateMessage("YUP"))
        }
    }

    private fun Button.initToast() {
        setOnClickListener {
            vm.process(HomeEvent.ToastEvent(tvText.text.toString()))
        }
    }

    override fun onStateUpdate(state: HomeState) {
        tvText.text = state.message
    }

    override fun onSingleEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.ToastEvent -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
            else -> throw Exception("Unhandled event ${event::class.java.canonicalName}")
        }
    }
}