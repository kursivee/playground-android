package com.kursivee.graphql.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kursivee.graphql.R
import com.kursivee.graphql.di.Scope
import com.kursivee.graphql.main.presentation.SessionViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class MainFragment : Fragment() {

    private val scope = getKoin().getOrCreateScope(Scope.SESSION_SCOPE.name, named(Scope.SESSION_SCOPE))
    private val vm: MainViewModel by scope.viewModel(this)
    private val sessionViewModel: SessionViewModel by scope.viewModel(this)

    val tvMessage: TextView by lazy {
        view!!.findViewById<TextView>(R.id.tv_message)
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
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