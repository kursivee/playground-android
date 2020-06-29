package com.kursivee.mvi.home.event

sealed class HomeStateEvent {
    data class UpdateMessage(val message: String): HomeStateEvent()
}