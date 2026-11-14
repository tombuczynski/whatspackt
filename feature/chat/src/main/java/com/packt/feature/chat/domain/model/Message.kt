package com.packt.feature.chat.domain.model

import com.packt.feature.chat.ui.model.MessageContent

/**
 * Created by Tom Buczynski on 06.06.2026.
 */
class Message(
    val id: String? = null,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String? = null,
    val isMine: Boolean,
    val contentType: ContentType,
    val content: String,
    val contentDescription: String,
) {
    enum class ContentType {
        TEXT, IMAGE
    }
}
