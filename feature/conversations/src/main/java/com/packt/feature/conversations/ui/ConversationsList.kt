package com.packt.feature.conversations.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.packt.feature.conversations.ui.model.Conversation
import com.packt.feature.conversations.ui.model.sampleConversationsList

/**
 * Created by Tom Buczynski on 18.02.2026.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConversationsList(
    modifier: Modifier = Modifier,
    conversations: List<Conversation> = sampleConversationsList()
) {
    Box() {

    }
}
