package com.kursivee.mvi.home.event

sealed class HomeEvent {
    data class UpdateMessage(val message: String): HomeEvent()
    data class ToastEvent(val message: String): HomeEvent()
}