package com.packt.common.framework.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.DrawableImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.packt.common.framework.R
import androidx.compose.ui.platform.LocalResources
import androidx.core.content.res.ResourcesCompat
import coil3.asImage

/**
 * Created by Tom Buczynski on 16.02.2026.
 */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Avatar(
    imageUrl: String,
    size: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String = "Avatar picture"
) {
    val res = LocalResources.current
    val previewHandler = AsyncImagePreviewHandler {
        //ColorImage(Color.Blue.toArgb())
        val avatarDrawable = ResourcesCompat.getDrawable(res, R.drawable.avatar150, null)
        avatarDrawable!!.asImage()
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            error = painterResource(R.drawable.crossedout150),
            modifier = modifier
                .size(size)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun AvatarPreview() {
        Avatar("https://i.pravatar.cc/150?u=2", 50.dp)
}