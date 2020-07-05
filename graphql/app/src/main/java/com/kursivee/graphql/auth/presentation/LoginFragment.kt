package com.kursivee.graphql.auth.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kursivee.graphql.R
import com.kursivee.graphql.databinding.LoginFragmentBinding
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
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        nullableBinding = null
        super.onDestroyView()
    }

}