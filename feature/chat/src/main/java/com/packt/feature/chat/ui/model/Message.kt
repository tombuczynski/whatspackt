package com.packt.feature.chat.ui.model

/**
 * Created by Tom Buczynski on 06.06.2026.
 */
data class Message(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String,
    val isMine: Boolean,
    val content: MessageContent
)

sealed class MessageContent {
    data class TextMessage(val text: String) : MessageContent()
    data class ImageMessage(
        val imageUrl: String,
        val imageDescription: String? = null
    ) : MessageContent()
}

fun sampleMessagesList(): List<Message> =
    listOf(
        Message(
            id = "1",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = false,
            timestamp = "10:00",
            content = MessageContent.TextMessage(
                text = "Hi, how are you?"
            )
        ),
        Message(
            id = "2",
            senderName = "Lucy",
            senderAvatar = "https://i.pravatar.cc/300?img=2",
            isMine = true,
            timestamp = "10:01",
            content = MessageContent.TextMessage(
                text = "I'm good, thank you! And you?"
            )
        ),
        Message(
            id = "3",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = false,
            timestamp = "10:02",
            content = MessageContent.TextMessage(
                text = "Super fine!"
            )
        ),
        Message(
            id = "4",
            senderName = "Lucy",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = true,
            timestamp = "11:01",
            content = MessageContent.TextMessage(
                text = "Are you going to the Kotlin conference?"
            )
        ),
        Message(
            id = "5",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = false,
            timestamp = "11:13",
            content = MessageContent.TextMessage(
                text = "Of course! I hope to see you there!"
            )
        ),
        Message(
            id = "6",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = false,
            timestamp = "11:15",
            content = MessageContent.TextMessage(
                text = "I'm going to arrive pretty early"
            )
        ),
        Message(
            id = "7",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = false,
            timestamp = "20:03",
            content = MessageContent.TextMessage(
                text = "So maybe we can have a coffee together"
            )
        ),
        Message(
            id = "8",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = false,
            timestamp = "20:05",
            content = MessageContent.TextMessage(
                text = "Wdyt?"
            )
        ),
        Message(
            id = "9",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=21",
            isMine = false,
            timestamp = "21:03",
            content = MessageContent.ImageMessage(
                imageUrl = "https://i.pravatar.cc/300?img=20",
                imageDescription = "Image of Alice"
            )
        ),
    )
