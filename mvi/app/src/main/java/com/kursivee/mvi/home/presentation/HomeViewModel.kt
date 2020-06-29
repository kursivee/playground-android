package com.kursivee.mvi.home.presentation

import androidx.lifecycle.viewModelScope
import com.kursivee.mvi.base.domain.event.NetworkEvent
import com.kursivee.mvi.base.presentation.framework.BaseViewModel
import com.kursivee.mvi.home.domain.GetMessageUseCase
import com.kursivee.mvi.home.presentation.event.HomeEvent
import com.kursivee.mvi.home.presentation.state.HomeState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMessageUseCase: GetMessageUseCase = GetMessageUseCase()
) : BaseViewModel<HomeState, HomeEvent>(HomeState("Hello")) {

    override fun process(event: HomeEvent) {
        when(event) {
            is HomeEvent.UpdateMessage -> {
                getMessage()
            }
            is HomeEvent.ToastEvent -> {
                sendSingleEvent(event)
            }
        }
    }

    private fun getMessage() {
        viewModelScope.launch {
            getMessageUseCase().collect { event ->
                when(event) {
                    is NetworkEvent.Loading -> {
                        updateState { it.copy(loading = true) }
                    }
                    is NetworkEvent.Success -> {
                        updateState { it.copy(message = event.data.message, loading = false) }
                    }
                    is NetworkEvent.Error -> {
                        updateState { it.copy(message = event.error.error, loading = false) }
                    }
                }
            }
        }
    }
}