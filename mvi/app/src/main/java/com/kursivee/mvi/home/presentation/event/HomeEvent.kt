package com.kursivee.mvi.home.presentation.event

sealed class HomeEvent {
    data class UpdateMessage(val message: String): HomeEvent()
    data class ToastEvent(val message: String): HomeEvent()
}