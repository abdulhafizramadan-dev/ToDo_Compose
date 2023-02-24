package com.ahr.todo_compose.ui.screen.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.todo_compose.R
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme
import com.ahr.todo_compose.util.Action

@Composable
fun TaskAppBar() {

}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = { BackAction(onBackClicked = navigateToListScreen) },
        title = { Text(text = "Add Task") },
        actions = { AddAction(onAddClicked = navigateToListScreen) }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back)
        )
    }
}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(R.string.save_task),
    )
    }
}

@Preview
@Composable
fun NewTaskAppBarPreview() {
    ToDoComposeTheme {
        Surface {
            NewTaskAppBar(navigateToListScreen = {})
        }
    }
}
