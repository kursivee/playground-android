package com.kursivee.graphql.ui.main

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
import com.kursivee.graphql.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    val tvMessage: TextView by lazy {
        view!!.findViewById<TextView>(R.id.tv_message)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(view!!) {
            findViewById<Button>(R.id.btn_memcache)?.setOnClickListener {
                viewModel.getUsersAndMemCache()
            }
            findViewById<Button>(R.id.btn_logout)?.setOnClickListener {
                viewModel.logout()
            }
            findViewById<Button>(R.id.btn_book)?.setOnClickListener {
                viewModel.loginAndBookTrip()
            }
            findViewById<Button>(R.id.btn_subscribe)?.setOnClickListener {
                viewModel.subscribe()
            }
            findViewById<Button>(R.id.btn_login)?.setOnClickListener {
                viewModel.login()
            }
        }

        viewModel.data.observe(viewLifecycleOwner, Observer {
            tvMessage.text = it
        })

        viewModel.subscription.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

}