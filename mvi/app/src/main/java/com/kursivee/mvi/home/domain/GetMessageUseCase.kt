package com.kursivee.mvi.home.domain

import com.kursivee.mvi.base.domain.entity.ErrorEntity
import com.kursivee.mvi.base.domain.event.NetworkEvent
import com.kursivee.mvi.base.domain.usecase.FlowUseCase
import com.kursivee.mvi.home.data.MessageRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
@FlowPreview
class GetMessageUseCase(
    private val messageRepository: MessageRepository = MessageRepositoryImpl()): FlowUseCase<MessageEntity, ErrorEntity>() {

    override suspend operator fun invoke(): Flow<NetworkEvent<MessageEntity, ErrorEntity>> {
        return run(
            messageRepository.getQuickMessage(),
            messageRepository.getMessage(),
            messageRepository.getMessage(),
            messageRepository.getQuickMessage()
        )
    }
}