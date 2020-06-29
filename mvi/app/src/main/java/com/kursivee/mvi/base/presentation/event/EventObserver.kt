package com.kursivee.mvi.base.presentation.event

import androidx.lifecycle.Observer

/**
 * An [Observer] for [SingleEvent]s, simplifying the pattern of checking if the [SingleEvent]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [SingleEvent]'s contents has not been handled.
 *
 * gist: https://gist.github.com/JoseAlcerreca/e0bba240d9b3cffa258777f12e5c0ae9
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<SingleEvent<T>> {
    override fun onChanged(event: SingleEvent<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}