package com.packt.feature.conversations.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.packt.common.framework.ui.navigation.GenerateTitles
import com.packt.common.framework.ui.navigation.NavLabel
import com.packt.common.framework.ui.navigation.tabsConversationsList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsListScreen(
    appTitle: String,
    onConversationClick: (String) -> Unit,
    onNewConversation: () -> Unit,
    modifier: Modifier = Modifier
) {
    tabsConversationsList.GenerateTitles()

    val pagerState = rememberPagerState(initialPage = tabsConversationsList.indexOf(NavLabel.Chats)) { tabsConversationsList.size }
    val corScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(appTitle) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                actions = {
                    IconButton( onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        bottomBar = {
            SecondaryTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = MaterialTheme.colorScheme.primaryContainer,

                ) {
                tabsConversationsList.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            corScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = tab.title) }
                    )
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                pagerState.currentPage == tabsConversationsList.indexOf(NavLabel.Chats),
                enter = scaleIn(),// expandIn(animationSpec = spring(stiffness = Spring.StiffnessHigh))
                exit = scaleOut()
            )  {
                FloatingActionButton(onClick = onNewConversation) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            modifier = Modifier.padding(paddingValues),
        ) { pageIndex ->
            Column(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = tabsConversationsList[pageIndex].title)
            }
        }
    }
}
