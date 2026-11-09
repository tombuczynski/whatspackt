package com.packt.feature.chat.data.network.model

import com.packt.feature.chat.data.network.model.WebsocketMessage.Companion.IMAGE_CONTENT_TYPE
import com.packt.feature.chat.domain.model.Message
import kotlinx.serialization.Serializable

/**
 * Created by Tom Buczynski on 02.11.2026.
 */
@Serializable
data class WebsocketMessage(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String,
    val isMine: Boolean,
    val contentType: String,
    val content: String,
    val contentDescription: String,
) {

    companion object {
        const val TEXT_CONTENT_TYPE = "TEXT"
        const val IMAGE_CONTENT_TYPE = "IMAGE"
    }

    private fun toMessageType(): Message.ContentType = when (contentType) {
        IMAGE_CONTENT_TYPE -> Message.ContentType.IMAGE
        else -> Message.ContentType.TEXT
    }

    fun toDomain() = Message(
        id = id,
        senderName = senderName,
        senderAvatar = senderAvatar,
        timestamp = timestamp,
        isMine = isMine,
        contentType = toMessageType(),
        content = content,
        contentDescription = contentDescription
    )
}

fun Message.fromDomain(): WebsocketMessage = WebsocketMessage(
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
