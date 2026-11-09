package com.packt.feature.chat.domain.model

/**
 * Created by Tom Buczynski on 06.06.2026.
 */
class Message(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String,
    val isMine: Boolean,
    val contentType: ContentType,
    val content: String,
    val contentDescription: String,
) {
    enum class ContentType {
        TEXT, IMAGE
    }
}

