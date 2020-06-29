package com.kursivee.mvi.home.presentation

import androidx.lifecycle.viewModelScope
import com.kursivee.mvi.base.presentation.framework.BaseViewModel
import com.kursivee.mvi.base.presentation.framework.State
import com.kursivee.mvi.home.domain.GetMessageUseCase
import com.kursivee.mvi.home.presentation.event.HomeEvent
import com.kursivee.mvi.home.presentation.state.HomeViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.cancel

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel constructor(
    private val getMessageUseCase: GetMessageUseCase = GetMessageUseCase()
) : BaseViewModel<HomeViewState, HomeEvent>(
    State(loading = false, view = HomeViewState("Hello"))
) {

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
        updateViewState { it.copy(message = "") }
        request(
            useCase = getMessageUseCase,
            onSuccess = { event ->
                updateViewState { it.copy(message = "${it.message} ${event.data.message}") }
            },
            onError = { event, scope ->
                updateViewState { it.copy(message = "${it.message} ${event.error.error}") }
            },
            terminateOnError = true
        )
    }
}