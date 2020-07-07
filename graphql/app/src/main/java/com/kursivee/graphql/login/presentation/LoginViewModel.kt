package com.kursivee.graphql.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursivee.graphql.login.domain.LoginUseCase
import com.kursivee.graphql.base.ui.event.SingleEvent
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val mutableState: MutableLiveData<SingleEvent<Boolean>> = MutableLiveData()
    val state: LiveData<SingleEvent<Boolean>> = mutableState

    fun login(email: String) {
        viewModelScope.launch {
            mutableState.value = SingleEvent(loginUseCase(email).isRight())
        }
    }
}