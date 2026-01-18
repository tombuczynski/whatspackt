package com.packt.common.framework.ui.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

/**
 * Created by Tom Buczynski on 02.01.2026.
 */

@Keep
@Serializable
sealed class Route {

    @Keep
    @Serializable
    object ConversationsList : Route()

    @Keep
    @Serializable
    object NewConversation : Route()

    @Keep
    @Serializable
    data class Chat(val id: String?) : Route()
}
