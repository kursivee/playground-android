package com.kursivee.graphql.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursivee.graphql.base.cache.domain.ClearSessionUseCase
import com.kursivee.graphql.home.domain.BookTripsUseCase
import com.kursivee.graphql.home.domain.SubscribeTripCountUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SessionViewModel(
    private val subscribeTripCountUseCase: SubscribeTripCountUseCase,
    private val clearSessionUseCase: ClearSessionUseCase
) : ViewModel() {

    private var subscriptionJob: Job? = null

    fun subscribeOnTripCount() {
        subscriptionJob = viewModelScope.launch {
            subscribeTripCountUseCase()
        }
    }

    fun logout() {
        subscriptionJob?.cancel()
        clearSessionUseCase()
    }
}