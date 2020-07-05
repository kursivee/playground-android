package com.kursivee.graphql.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kursivee.graphql.R
import com.kursivee.graphql.main.presentation.MainActivity
import com.kursivee.graphql.main.presentation.SessionViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val vm: MainViewModel by viewModel()
    private val sessionViewModel: SessionViewModel by lazy {
        (requireActivity() as MainActivity).vm
    }

    val tvMessage: TextView by lazy {
        view!!.findViewById<TextView>(R.id.tv_message)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(view!!) {
            findViewById<Button>(R.id.btn_logout)?.setOnClickListener {
                sessionViewModel.logout()
            }
            findViewById<Button>(R.id.btn_book)?.setOnClickListener {
                vm.bookTrip()
            }
            findViewById<Button>(R.id.btn_subscribe)?.setOnClickListener {
                sessionViewModel.subscribeOnTripCount()
                vm.observeTripCount()
            }
            findViewById<Button>(R.id.btn_login)?.setOnClickListener {
                vm.login()
            }
        }

        vm.data.observe(viewLifecycleOwner, Observer {
            tvMessage.text = it
        })

        vm.subscription.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

}