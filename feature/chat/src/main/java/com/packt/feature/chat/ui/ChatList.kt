package com.packt.feature.chat.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.packt.feature.chat.ui.model.Message
import com.packt.feature.chat.ui.model.sampleMessagesList

/**
 * Created by Tom Buczynski on 13.07.2026.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatList(
    modifier: Modifier = Modifier,
    messages: List<Message> = sampleMessagesList(),
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(8.dp)) {
        items(
            messages,
            key = { it -> it.id }
        ) {
            ChatItem(it, Modifier.fillMaxWidth())
        }
    }
}
