package com.kursivee.mvi.home.state

import com.kursivee.mvi.common.ui.BaseState

data class HomeState(
    val message: String,
    override val loading: Boolean = false
): BaseState