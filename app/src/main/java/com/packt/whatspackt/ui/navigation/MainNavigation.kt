package com.packt.whatspackt.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.packt.common.framework.ui.navigation.ChatDeepLink
import com.packt.common.framework.ui.navigation.Route
import com.packt.feature.chat.ui.ChatScreen
import com.packt.feature.conversations.ui.ConversationsListScreen
import com.packt.feature.create_chat.ui.CreateNewChatScreen
import com.packt.whatspackt.R

/**
 * Created by Tom Buczynski on 02.01.2026.
 */
@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val appTitle = stringResource(R.string.app_name)

    NavHost(
        navController = navController,
        startDestination = Route.ConversationsList,
        modifier = modifier
    ) {
        composable<Route.ConversationsList> {
            ConversationsListScreen(
                appTitle = appTitle,
                onConversationClick = {
                    navController.navigate(Route.Chat(it)) {
                        launchSingleTop = true
                    }
                },
                onNewConversation = { navController.navigate(Route.NewConversation) },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable<Route.NewConversation> {
            CreateNewChatScreen(
                onCreateNewChat = {
                     navController.navigate(Route.Chat(null))     {
                         popUpTo(Route.ConversationsList)
                     }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable<Route.Chat>(
            deepLinks = listOf(navDeepLink<Route.Chat>(ChatDeepLink))
        ) { backStackEntry ->

            val id = backStackEntry.toRoute<Route.Chat>().id

            ChatScreen(id, { navController.popBackStack() },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}