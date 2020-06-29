package com.kursivee.mvi.common.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kursivee.mvi.common.EventObserver

abstract class BaseFragment<State: BaseState, Event, VM: BaseViewModel<State, Event>>: Fragment() {

    protected abstract val vm: VM

    protected abstract fun onStateUpdate(state: State)
    protected abstract fun onSingleEvent(event: Event)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.state.observe(viewLifecycleOwner, Observer { onStateUpdate(it) })
        vm.event.observe(viewLifecycleOwner, EventObserver { onSingleEvent(it) })
    }
}