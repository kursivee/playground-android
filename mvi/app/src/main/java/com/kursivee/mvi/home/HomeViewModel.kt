package com.kursivee.mvi.home

import com.kursivee.mvi.common.ui.BaseViewModel
import com.kursivee.mvi.home.event.HomeEvent
import com.kursivee.mvi.home.state.HomeState

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