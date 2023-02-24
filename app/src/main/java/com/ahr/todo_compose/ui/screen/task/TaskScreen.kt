package com.ahr.todo_compose.ui.screen.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskScreen() {
    Scaffold(
        topBar = { }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
    }
}