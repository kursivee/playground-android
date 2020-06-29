package com.kursivee.mvi.home.event

sealed class HomeViewEvent {
    data class UpdateMessage(val message: String): HomeViewEvent()
}