package com.ahr.todo_compose.ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.todo_compose.R
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            ListFab { navigateToTaskScreen(-1) }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}

@Composable
fun ListFab(onFabClicked: () -> Unit) {
    FloatingActionButton(onClick = onFabClicked) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_new_task)
        )
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    ToDoComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ListScreen(navigateToTaskScreen = {})
        }
    }
}
