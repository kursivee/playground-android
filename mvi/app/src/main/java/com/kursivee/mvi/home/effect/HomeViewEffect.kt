package com.kursivee.mvi.home.effect

sealed class HomeViewEffect {
    object NavigateScreen: HomeViewEffect()
    data class ToastEffect(val message: String): HomeViewEffect()
}