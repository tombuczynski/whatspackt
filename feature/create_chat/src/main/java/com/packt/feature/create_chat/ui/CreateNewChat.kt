package com.packt.feature.create_chat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Tom Buczynski on 15.01.2026.
 */
@Composable
fun CreateNewChat(
    onCreateNewChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(8.dp)) {
        Text(text = "New Conversation")
        Button(onCreateNewChat) {
            Text(text = "Create New Chat")
        }
    }
}
