package com.kursivee.mvi.home.domain

import com.kursivee.mvi.base.domain.entity.ErrorEntity
import com.kursivee.mvi.base.domain.event.NetworkEvent
import com.kursivee.mvi.home.data.MessageRepositoryImpl
import kotlinx.coroutines.flow.Flow

class GetMessageUseCase(private val messageRepository: MessageRepository = MessageRepositoryImpl()) {
    suspend operator fun invoke(): Flow<NetworkEvent<MessageEntity, ErrorEntity>> {
        return messageRepository.getMessage()
    }
}