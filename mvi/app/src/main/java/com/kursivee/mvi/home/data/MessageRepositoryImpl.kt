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
            emit(NetworkEvent.Loading)
            delay(2000)
            if((0..10).random() % 2 == 0) {
                emit(NetworkEvent.Success(MessageEntity("YO")))
            } else {
                emit(
                    NetworkEvent.Error(
                    ErrorEntity(
                        "ERROR"
                    )
                ))
            }
        }
    }
}