package com.packt.feature.create_chat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Tom Buczynski on 15.01.2026.
 */
@Composable
fun CreateNewChatScreen(
    onCreateNewChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(
            modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "New Conversation")
            Button(onCreateNewChat) {
                Text(text = "Create New Chat")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCreateNewChatScreen() {
    CreateNewChatScreen(onCreateNewChat = {}, Modifier.fillMaxSize())
}
