package com.kursivee.mvi.home.data

import com.kursivee.mvi.base.domain.entity.ErrorEntity
import com.kursivee.mvi.base.domain.event.NetworkEvent
import com.kursivee.mvi.home.domain.MessageEntity
import com.kursivee.mvi.home.domain.MessageRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessageRepositoryImpl: MessageRepository {
    override suspend fun getMessage(): Flow<NetworkEvent<MessageEntity, ErrorEntity>> {
        return flow {
            delay(((0..5000).random()+ 1000).toLong())
            if((0..10).random() % 2 == 0) {
                emit(NetworkEvent.Success(MessageEntity("SUCCESS ${(0..100).random()}")))
            } else {
                emit(NetworkEvent.Error(ErrorEntity("ERROR")))
            }
        }
    }
}