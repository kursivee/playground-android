package com.kursivee.mvi.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursivee.mvi.common.Event
import com.kursivee.mvi.home.effect.HomeViewEffect
import com.kursivee.mvi.home.event.HomeViewEvent
import com.kursivee.mvi.home.state.HomeViewState

class HomeViewModel : ViewModel() {
    private val _state: MutableLiveData<HomeViewState> = MutableLiveData(HomeViewState("hello"))
    val state: LiveData<HomeViewState> = _state

    private val _effect: MutableLiveData<Event<HomeViewEffect>>  = MutableLiveData()
    val effect: LiveData<Event<HomeViewEffect>> = _effect

    init {
        println("HERE")
    }

    fun process(event: HomeViewEvent) {
        when(event) {
            is HomeViewEvent.UpdateMessage -> {
                _state.value = state.value?.copy(message = event.message)
            }
        }
    }

    fun process(effect: HomeViewEffect) {
        _effect.value = Event(effect)
    }
}