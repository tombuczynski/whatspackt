package com.packt.feature.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packt.feature.chat.domain.usecases.DisconnectMessages
import com.packt.feature.chat.domain.usecases.RetrieveMessages
import com.packt.feature.chat.domain.usecases.SendMessage
import com.packt.feature.chat.ui.model.Message
import com.packt.feature.chat.ui.model.MessageContent
import dagger.hilt.android.lifecycle.HiltViewModel
import com.packt.feature.chat.domain.model.Message as DomainMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Tom Buczynski on 10.11.2026.
 */
@HiltViewModel
class ChatViewModel @Inject constructor (
    private val retrieveMessages: RetrieveMessages,
    private val sendMessage: SendMessage,
    private val disconnectMessages: DisconnectMessages
) : ViewModel() {

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private var updateMessagesJob: Job? = null

    fun updateMessages() {
        updateMessagesJob = viewModelScope.launch(Dispatchers.IO) {
            retrieveMessages()
                .map { it.toUI() }
                .collect { msg ->
                    _messages.update { it + msg }
                }
        }
    }

    fun sendTextMessage(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sendMessage(
                DomainMessage(
                    senderName = TODO(),
                    senderAvatar = TODO(),
                    isMine = true,
                    contentType = DomainMessage.ContentType.TEXT,
                    content = text,
                    contentDescription = ""
                )
            )
        }
    }

    override fun onCleared() {
        updateMessagesJob?.cancel()

        viewModelScope.launch(Dispatchers.IO) {
            disconnectMessages()
        }
    }
}

private fun DomainMessage.toUI() =
    Message(
        id = id ?: "",
        senderName = senderName,
        senderAvatar = senderAvatar,
        timestamp = timestamp ?: "",
        isMine = isMine,
        content = toMessageContentUI()
    )

private fun DomainMessage.toMessageContentUI() =
    when(contentType) {
        DomainMessage.ContentType.TEXT -> MessageContent.TextMessage(content)
        DomainMessage.ContentType.IMAGE -> MessageContent.ImageMessage(content, contentDescription)
    }

        
