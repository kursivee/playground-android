package com.kursivee.mvi.home.event

sealed class HomeSingleEvent {
    object NavigateScreen: HomeSingleEvent()
    data class ToastEvent(val message: String): HomeSingleEvent()
}