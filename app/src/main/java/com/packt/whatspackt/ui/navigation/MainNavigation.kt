package com.packt.whatspackt.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.packt.common.framework.ui.navigation.ChatDeepLink
import com.packt.feature.chat.ui.ChatScreen
import com.packt.feature.conversations.ui.ConversationsScreen
import com.packt.feature.create_chat.ui.CreateNewChat

/**
 * Created by Tom Buczynski on 02.01.2026.
 */
@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Conversations,
        modifier = modifier.padding(top = 16.dp)
    ) {
        composable<Route.Conversations> {
            ConversationsScreen(
                onConversationClick = {
                    navController.navigate(Route.Chat(it)) {
                        launchSingleTop = true
                    }
                },
                onNewConversation = { navController.navigate(Route.NewConversation) })
        }

        composable<Route.NewConversation> {
            CreateNewChat(
                onCreateNewChat = {
                     navController.navigate(Route.Chat(null))     {
                         popUpTo(Route.Conversations)
                     }
                }
            )
        }

        composable<Route.Chat>(
            deepLinks = listOf(navDeepLink<Route.Chat>(ChatDeepLink))
        ) { backStackEntry ->

            val id = backStackEntry.toRoute<Route.Chat>().id

            ChatScreen(id, { navController.popBackStack() })
        }
    }
}