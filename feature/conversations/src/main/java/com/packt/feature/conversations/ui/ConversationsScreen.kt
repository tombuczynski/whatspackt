package com.packt.feature.conversations.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Tom Buczynski on 06.01.2026.
 */

@Composable
fun ConversationsScreen(
    onConversationClick: (String) -> Unit,
    onNewConversation: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.clickable { onConversationClick("1") }) {
        Text(text = "Conversations List")
        Button(onNewConversation) {
            Text(text = "New Conversation")
        }
    }
}