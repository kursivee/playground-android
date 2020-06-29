package com.kursivee.mvi.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursivee.mvi.common.Event
import com.kursivee.mvi.home.event.HomeSingleEvent
import com.kursivee.mvi.home.event.HomeStateEvent
import com.kursivee.mvi.home.state.HomeViewState

class HomeViewModel : ViewModel() {
    private val _state: MutableLiveData<HomeViewState> = MutableLiveData(HomeViewState("hello"))
    val state: LiveData<HomeViewState> = _state

    private val _event: MutableLiveData<Event<HomeSingleEvent>>  = MutableLiveData()
    val event: LiveData<Event<HomeSingleEvent>> = _event

    fun process(event: HomeStateEvent) {
        when(event) {
            is HomeStateEvent.UpdateMessage -> {
                _state.value = state.value?.copy(message = event.message)
            }
        }
    }

    fun process(effect: HomeSingleEvent) {
        _event.value = Event(effect)
    }
}