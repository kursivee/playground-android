package com.kursivee.mvi.base.domain.usecase

import com.kursivee.mvi.base.domain.event.NetworkEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
abstract class FlowUseCase<S, E> {

    fun run(vararg flows: Flow<NetworkEvent<S, E>>): Flow<NetworkEvent<S, E>> {
        val loadingFlow = flow {
            emit(NetworkEvent.Loading)
        }
        return flowOf(loadingFlow, *flows)
            .flattenMerge()
            .onCompletion {
                emit(NetworkEvent.Completed)
            }
    }

    abstract suspend operator fun invoke(): Flow<NetworkEvent<S, E>>
}