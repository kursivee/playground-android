package com.kursivee.mvi.base.presentation.framework

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kursivee.mvi.base.presentation.event.EventObserver

abstract class BaseFragment<ViewState, Event, VM: BaseViewModel<ViewState, Event>>: Fragment() {

    protected abstract val vm: VM

    protected abstract fun onStateUpdate(state: State<ViewState>)
    protected abstract fun onSingleEvent(event: Event)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.state.observe(viewLifecycleOwner, Observer { onStateUpdate(it) })
        vm.event.observe(viewLifecycleOwner,
            EventObserver {
                onSingleEvent(
                    it
                )
            })
    }
}