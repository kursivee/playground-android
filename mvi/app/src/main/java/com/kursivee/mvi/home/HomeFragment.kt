package com.kursivee.mvi.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kursivee.mvi.R
import com.kursivee.mvi.home.event.HomeSingleEvent
import com.kursivee.mvi.home.event.HomeStateEvent
import com.kursivee.mvi.home.state.HomeViewState

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var tvText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        tvText = requireView().findViewById(R.id.tv_text)
        requireView().findViewById<Button>(R.id.btn_button).init()
        requireView().findViewById<Button>(R.id.btn_effect).initEffect()

        viewModel.state.observe(viewLifecycleOwner, Observer { render(it) })
        viewModel.event.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { effect ->
                onEffect(effect)
            }
        })
    }

    private fun Button.init() {
        setOnClickListener {
            viewModel.process(HomeStateEvent.UpdateMessage("YUP"))
        }
    }

    private fun Button.initEffect() {
        setOnClickListener {
            viewModel.process(HomeSingleEvent.ToastEffect(tvText.text.toString()))
        }
    }

    private fun render(state: HomeViewState) {
        tvText.text = state.message
    }

    private fun onEffect(effect: HomeSingleEvent) {
        when(effect) {
            is HomeSingleEvent.ToastEffect -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}