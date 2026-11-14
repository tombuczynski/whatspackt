package com.packt.feature.chat.data.network.datasource

import android.util.Log
import com.packt.feature.chat.data.network.model.WebsocketMessage
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
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * Created by Tom Buczynski on 26.10.2026.
 */
private const val TAG = "MessagesSocketDataSource"

class MessagesSocketDataSource @Inject constructor (
    private val httpClient: HttpClient,
    private val websocketUrl: String
) {
    private lateinit var webSocketSession: DefaultClientWebSocketSession

    suspend fun connect(): Flow<WebsocketMessage> {
        webSocketSession = httpClient.webSocketSession {
            url(websocketUrl)
        }

        return webSocketSession
            .incoming
            .receiveAsFlow()
            .mapNotNull { frame -> handleFrame(frame) }
    }

    suspend fun send(msg: WebsocketMessage) {
        webSocketSession.sendSerialized(msg)
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
