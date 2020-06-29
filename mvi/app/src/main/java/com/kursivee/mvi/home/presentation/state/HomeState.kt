package com.kursivee.mvi.home.presentation.state

import com.kursivee.mvi.base.presentation.framework.BaseState

data class HomeState(
    val message: String,
    override val loading: Boolean = false
): BaseState