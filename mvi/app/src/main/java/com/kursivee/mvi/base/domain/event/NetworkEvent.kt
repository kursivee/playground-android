package com.kursivee.mvi.base.domain.event

sealed class NetworkEvent<out Success, out Error> {
    object Loading: NetworkEvent<Nothing, Nothing>()
    object Completed: NetworkEvent<Nothing, Nothing>()
    data class Success<Success>(val data: Success): NetworkEvent<Success, Nothing>()
    data class Error<Error> (val error: Error): NetworkEvent<Nothing, Error>()
}