package com.kursivee.mvi.base.presentation.framework

data class State<ViewState>(
    val loading: Boolean = false,
    val view: ViewState
)