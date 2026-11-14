package com.packt.feature.chat.domain

import com.packt.feature.chat.domain.model.Message
import kotlinx.coroutines.flow.Flow

/**
 * Created by Tom Buczynski on 09.11.2026.
 */
interface IMessagesRepository {
    suspend fun retrieveMessages(): Flow<Message>

    suspend fun sendMessage(msg: Message)

    suspend fun disconnect()
}