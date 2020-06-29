package com.kursivee.mvi.base.presentation.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursivee.mvi.base.presentation.event.SingleEvent

abstract class BaseViewModel<State: BaseState, Event>(initialState: State): ViewModel() {
    private val _state: MutableLiveData<State> = MutableLiveData(initialState)
    val state: LiveData<State> = _state

    private val _event: MutableLiveData<SingleEvent<Event>> = MutableLiveData()
    val event: LiveData<SingleEvent<Event>> = _event

    abstract fun process(event: Event)

    protected fun updateState(copy: (State) -> State) {
        _state.value = copy(_state.value!!)
    }

    protected fun sendSingleEvent(event: Event) {
        _event.value =
            SingleEvent(event)
    }
}