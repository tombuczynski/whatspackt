package com.packt.feature.chat.data.network.datasource

import android.util.Log
import com.packt.feature.chat.data.network.model.WebsocketMessage
import com.packt.feature.chat.data.network.model.fromDomain
import com.packt.feature.chat.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.converter
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.serialization.WebsocketConverterNotFoundException
import io.ktor.serialization.deserialize
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readReason
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Created by Tom Buczynski on 26.10.2026.
 */
private const val TAG = "MessagesSocketDataSource"

class MessagesSocketDataSource constructor (
    private val httpClient: HttpClient
) {
    private lateinit var webSocketSession: DefaultClientWebSocketSession

    suspend fun connect(url: String): Flow<Message> {
        webSocketSession = httpClient.webSocketSession {
            url(url)
        }

        return webSocketSession
            .incoming
            .receiveAsFlow()
            .mapNotNull { frame -> handleFrame(frame) }
            .map { it.toDomain() }
    }

    suspend fun send(msg: Message) {
        webSocketSession.sendSerialized(msg.fromDomain())
    }

    suspend fun disconnect() {
        webSocketSession.close(CloseReason(CloseReason.Codes.NORMAL, "Client has disconnected"))
    }

    private suspend fun handleFrame(frame: Frame): WebsocketMessage? {
        val converter = webSocketSession.converter
            ?: throw WebsocketConverterNotFoundException("No converter was found for websocket")

        return when (frame) {
            is Frame.Close -> {
                disconnect()
                Log.d(TAG, "handleFrame: Disconnected ${frame.readReason()?.message ?: ""}")
                null
            }
            is Frame.Text -> converter.deserialize(frame)
            else -> {
                Log.d(TAG, "handleFrame: ERROR")
                null
            }
        }
    }
}
