package com.kursivee.graphql.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kursivee.graphql.R
import com.kursivee.graphql.base.ui.event.EventObserver
import com.kursivee.graphql.databinding.LoginFragmentBinding
import com.kursivee.graphql.base.ui.event.SingleEvent
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var nullableBinding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding
        get() = nullableBinding!!

    private val vm: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        nullableBinding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnLogin.setOnClickListener {
            vm.login(binding.etEmail.text.toString())
        }
        vm.state.observe()
    }

    private fun LiveData<SingleEvent<Boolean>>.observe() {
        observe(viewLifecycleOwner, EventObserver { isSuccess ->
            if(isSuccess) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(context, R.string.login_error_msg, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        nullableBinding = null
        super.onDestroyView()
    }

}