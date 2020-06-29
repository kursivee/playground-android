package com.kursivee.mvi.base.domain.usecase

import com.kursivee.mvi.base.domain.event.NetworkEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

abstract class FlowUseCase<S, E> {

    fun run(vararg flows: Flow<NetworkEvent<S, E>>): Flow<NetworkEvent<S, E>> {
        val loadingFlow = flow {
            emit(NetworkEvent.Loading)
        }
        return flowOf(loadingFlow, *flows)
            .flattenMerge()
    }

    abstract suspend operator fun invoke(): Flow<NetworkEvent<S, E>>
}