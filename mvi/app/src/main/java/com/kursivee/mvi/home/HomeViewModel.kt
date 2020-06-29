package com.kursivee.mvi.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursivee.mvi.home.effect.HomeViewEffect
import com.kursivee.mvi.home.event.HomeViewEvent
import com.kursivee.mvi.home.state.HomeViewState

class HomeViewModel : ViewModel() {
    private val _state: MutableLiveData<HomeViewState> = MutableLiveData(HomeViewState("hello"))
    val state: LiveData<HomeViewState> = _state

    fun process(event: HomeViewEvent) {
        when(event) {
            is HomeViewEvent.UpdateMessage -> {
                _state.value = state.value?.copy(message = event.message)
            }
        }
    }

    fun process(effect: HomeViewEffect) {

    }
}