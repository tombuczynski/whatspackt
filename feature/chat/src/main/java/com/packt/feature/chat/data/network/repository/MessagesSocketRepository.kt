package com.packt.feature.chat.data.network.repository

import com.packt.feature.chat.data.network.datasource.MessagesSocketDataSource
import com.packt.feature.chat.data.network.model.WebsocketMessage
import com.packt.feature.chat.data.network.model.WebsocketMessage.Companion.IMAGE_CONTENT_TYPE
import com.packt.feature.chat.domain.IMessagesRepository
import com.packt.feature.chat.domain.model.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Tom Buczynski on 09.11.2026.
 */
class MessagesSocketRepository @Inject constructor (
    private val source: MessagesSocketDataSource
) : IMessagesRepository {
    override suspend fun retrieveMessages(): Flow<Message> {
       return source.connect().map { it.toDomain() }
    }

    override suspend fun sendMessage(msg: Message) {
        source.send(msg.fromDomain())
    }

    override suspend fun disconnect() {
        source.disconnect()
    }
}

private fun WebsocketMessage.toDomain() = Message(
    id = id,
    senderName = senderName,
    senderAvatar = senderAvatar,
    timestamp = timestamp,
    isMine = isMine,
    contentType = toMessageType(),
    content = content,
    contentDescription = contentDescription
)

private fun Message.fromDomain(): WebsocketMessage = WebsocketMessage(
    id = id,
    senderName = senderName,
    senderAvatar = senderAvatar,
    timestamp = timestamp,
    isMine = isMine,
    contentType = fromMessageType(),
    content = content,
    contentDescription = contentDescription
)


private fun Message.fromMessageType(): String = when (contentType) {
    Message.ContentType.IMAGE -> WebsocketMessage.IMAGE_CONTENT_TYPE
    else -> WebsocketMessage.TEXT_CONTENT_TYPE
}

private fun WebsocketMessage.toMessageType(): Message.ContentType = when (contentType) {
    IMAGE_CONTENT_TYPE -> Message.ContentType.IMAGE
    else -> Message.ContentType.TEXT
}
