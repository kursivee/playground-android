package com.kursivee.mvi.home.event

sealed class HomeSingleEvent {
    object NavigateScreen: HomeSingleEvent()
    data class ToastEffect(val message: String): HomeSingleEvent()
}