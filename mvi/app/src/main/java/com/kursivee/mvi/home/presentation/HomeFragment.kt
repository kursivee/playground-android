package com.kursivee.mvi.home.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.kursivee.mvi.R
import com.kursivee.mvi.base.presentation.framework.BaseFragment
import com.kursivee.mvi.base.presentation.framework.State
import com.kursivee.mvi.home.presentation.event.HomeEvent
import com.kursivee.mvi.home.presentation.state.HomeViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment<HomeViewState, HomeEvent, HomeViewModel>() {

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

    override fun onStateUpdate(state: State<HomeViewState>) {
        tvText.text = state.view.message
        if(state.loading) {
            requireActivity().findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        } else {
            requireActivity().findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
        }
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