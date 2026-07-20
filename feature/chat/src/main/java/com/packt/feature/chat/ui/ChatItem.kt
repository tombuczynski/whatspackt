package com.packt.feature.chat.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.packt.common.framework.R
import com.packt.common.framework.ui.Avatar
import com.packt.feature.chat.ui.model.Message
import com.packt.feature.chat.ui.model.MessageContent
import com.packt.feature.chat.ui.model.sampleMessagesList

/**
 * Created by Tom Buczynski on 17.02.2026.
 */

@Composable
fun ChatItem(msg: Message, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = if (msg.isMine) Arrangement.End else Arrangement.Start
    ) {
        if (!msg.isMine) {
            Avatar(msg.senderAvatar, 40.dp, contentDescription = "${msg.senderName}'s avatar")
        }

        Spacer(Modifier.width(8.dp))

        Column  {

            if (msg.isMine) {
                Spacer(Modifier.height(8.dp))
            } else {
                Text(
                    text = msg.senderName,
                    fontWeight = FontWeight.Bold
                )
            }

            when (val content = msg.content) {
                is MessageContent.TextMessage -> {
                    val bkgColor = if (!msg.isMine) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    }

                    Surface(shape = RoundedCornerShape(8.dp), color = bkgColor) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = content.text,
                            color = contentColorFor(bkgColor),
                        )
                    }
                }

                is MessageContent.ImageMessage -> {
                    AsyncImage(
                        model = content.imageUrl,
                        contentDescription = content.imageDescription,
                        error = painterResource(R.drawable.crossedout150),
                        modifier = Modifier
                            .height(40.dp),
                        contentScale = ContentScale.Fit
                    )

                }
            }

            Text(
                text = msg.timestamp,
                fontSize = 12.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ChatItemPreview1() {
    ChatItem(sampleMessagesList().first(), Modifier.fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
private fun ChatItemPreview2() {
    ChatItem(sampleMessagesList()[1], Modifier.fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
private fun ChatItemPreview3() {
    ChatItem(sampleMessagesList().last(), Modifier.fillMaxWidth())
}
