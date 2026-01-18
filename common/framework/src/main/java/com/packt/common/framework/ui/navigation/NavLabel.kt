package com.packt.common.framework.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.packt.common.framework.R

/**
 * Created by Tom Buczynski on 18.01.2026.
 */
sealed class NavLabel(val titleResId: Int = -1, var title: String = "?") {
    data object Status : NavLabel(R.string.nav_label_status)
    data object Chats : NavLabel(R.string.nav_label_chats)
    data object Calls : NavLabel(R.string.nav_label_calls)
}

val tabsConversationsList = listOf(NavLabel.Status, NavLabel.Chats, NavLabel.Calls)

@Composable
@ReadOnlyComposable
fun List<NavLabel>.GenerateTitles() {
    this.forEach {
        it.title = stringResource(it.titleResId)
    }
}
