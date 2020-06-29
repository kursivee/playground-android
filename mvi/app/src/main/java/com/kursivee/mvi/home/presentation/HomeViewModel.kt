package com.kursivee.mvi.home.presentation

import com.kursivee.mvi.base.presentation.framework.BaseViewModel
import com.kursivee.mvi.home.presentation.event.HomeEvent
import com.kursivee.mvi.home.presentation.state.HomeState

class HomeViewModel : BaseViewModel<HomeState, HomeEvent>(HomeState("Hello")) {

    override fun process(event: HomeEvent) {
        when(event) {
            is HomeEvent.UpdateMessage -> {
                updateState { it.copy(message = event.message) }
            }
            is HomeEvent.ToastEvent -> {
                sendSingleEvent(event)
            }
        }
    }
}