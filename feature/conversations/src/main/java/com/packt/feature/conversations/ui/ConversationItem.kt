package com.packt.feature.conversations.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.packt.common.framework.ui.Avatar
import com.packt.feature.conversations.ui.model.Conversation
import com.packt.feature.conversations.ui.model.sampleConversationsList

/**
 * Created by Tom Buczynski on 17.02.2026.
 */

@Composable
fun ConversationItem(conv: Conversation, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(conv.avatar, 50.dp, contentDescription = "${conv.name}'s avatar")

        Column(modifier = Modifier.fillMaxWidth(0.75f)) {
            Text(
                text = conv.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = conv.message,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = conv.timestamp,
            )

            if (conv.unreadCount > 0) {
                val shapeColor: Color = MaterialTheme.colorScheme.primary
                val fontColor: Color = MaterialTheme.colorScheme.onPrimary

                Spacer(Modifier.height(4.dp))
                Text(
                    text = conv.unreadCount.toString(),
                    color = fontColor,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(IntrinsicSize.Min)
                        .drawBehind() {
                            drawCircle(shapeColor, radius = size.maxDimension * 0.52f)
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 210)
@Preview(showBackground = true)
@Composable
private fun ConversationItemPreview() {
    ConversationItem(sampleConversationsList().first(), Modifier.fillMaxWidth())
}