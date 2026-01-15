package com.packt.feature.chat.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by Tom Buczynski on 06.01.2026.
 */

@Composable
fun ChatScreen(chatId: String?, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Text(text = "Chat: $chatId")
}