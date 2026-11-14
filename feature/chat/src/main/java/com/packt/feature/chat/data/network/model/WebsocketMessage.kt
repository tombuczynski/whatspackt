package com.packt.feature.chat.data.network.model

import kotlinx.serialization.Serializable

/**
 * Created by Tom Buczynski on 02.11.2026.
 */
@Serializable
data class WebsocketMessage(
    val id: String? = null,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String? = null,
    val isMine: Boolean,
    val contentType: String,
    val content: String,
    val contentDescription: String,
) {

    companion object {
        const val TEXT_CONTENT_TYPE = "TEXT"
        const val IMAGE_CONTENT_TYPE = "IMAGE"
    }
}
