package com.kursivee.graphql.main.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursivee.graphql.login.domain.LoginUseCase
import com.kursivee.graphql.booking.domain.BookTripsUseCase
import com.kursivee.graphql.booking.domain.ObserveTripCountUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val loginUseCase: LoginUseCase,
    private val bookTripsUseCase: BookTripsUseCase,
    private val observeTripCountUseCase: ObserveTripCountUseCase
) : ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()
    val subscription: MutableLiveData<String> = MutableLiveData()

    fun bookTrip() {
        viewModelScope.launch {
            bookTripsUseCase(listOf("83"))
        }
    }

    fun observeTripCount() {
        viewModelScope.launch {
            observeTripCountUseCase().collect {
                subscription.value = it.toString()
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase("email@a.com")
        }
    }
}