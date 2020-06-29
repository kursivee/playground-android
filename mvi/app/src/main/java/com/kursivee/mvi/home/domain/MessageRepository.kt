package com.kursivee.mvi.home.domain

import com.kursivee.mvi.base.domain.entity.ErrorEntity
import com.kursivee.mvi.base.domain.event.NetworkEvent
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun getMessage(): Flow<NetworkEvent<MessageEntity, ErrorEntity>>
}