package com.kursivee.graphql.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursivee.graphql.auth.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(email: String) {
        viewModelScope.launch {
            loginUseCase(email)
        }
    }
}