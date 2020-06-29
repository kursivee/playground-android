package com.kursivee.mvi.base.presentation.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursivee.mvi.base.domain.event.NetworkEvent
import com.kursivee.mvi.base.domain.usecase.FlowUseCase
import com.kursivee.mvi.base.presentation.event.SingleEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel<ViewState, Event>(initialState: State<ViewState>): ViewModel() {
    private val _state: MutableLiveData<State<ViewState>> = MutableLiveData(initialState)
    val state: LiveData<State<ViewState>> = _state

    private val _event: MutableLiveData<SingleEvent<Event>> = MutableLiveData()
    val event: LiveData<SingleEvent<Event>> = _event

    abstract fun process(event: Event)

    protected fun updateBaseState(copy: (State<ViewState>) -> State<ViewState>) {
        _state.value = copy(_state.value!!)
    }

    protected fun updateViewState(copy: (ViewState) -> ViewState) {
        _state.value = _state.value!!.copy(view = copy(_state.value!!.view))
    }

    protected fun sendSingleEvent(event: Event) {
        _event.value = SingleEvent(event)
    }

    fun <S, E>request(
        useCase: FlowUseCase<S, E>,
        onSuccess: (NetworkEvent.Success<S>) -> Unit,
        onError: (NetworkEvent.Error<E>) -> Unit
    ) {
        viewModelScope.launch {
            useCase().collect { event ->
                when(event) {
                    is NetworkEvent.Loading -> updateBaseState { it.copy(loading = true) }
                    is NetworkEvent.Success -> onSuccess(event)
                    is NetworkEvent.Error -> onError(event)
                    is NetworkEvent.Completed -> updateBaseState { it.copy(loading = false) }
                }
            }
        }
    }
}