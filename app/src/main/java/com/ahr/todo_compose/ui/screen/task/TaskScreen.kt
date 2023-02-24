package com.ahr.todo_compose.ui.screen.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ahr.todo_compose.util.Action

@Composable
fun TaskScreen(
    taskId: Int,
    navigateToListScreen: (Action) -> Unit,
) {
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
    }
}