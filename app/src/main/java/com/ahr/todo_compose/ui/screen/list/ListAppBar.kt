package com.ahr.todo_compose.ui.screen.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.todo_compose.R
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.ui.component.PriorityItem
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteAllClicked = {}
    )
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteAllClicked = onDeleteAllClicked
            )
        }
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteAllClicked = onDeleteAllClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onSearchClicked, modifier = modifier) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(R.string.search_tasks)
        )
    }
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {

    var dropdownState by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { dropdownState = !dropdownState }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = stringResource(R.string.sort_tasks)
        )
        DropdownMenu(
            expanded = dropdownState,
            onDismissRequest = { dropdownState = !dropdownState }
        ) {
            val filterPriorities = Priority.values().filter { it != Priority.MEDIUM }
            filterPriorities.forEach { priority ->
                DropdownMenuItem(onClick = {
                    dropdownState = !dropdownState
                    onSortClicked(priority)
                }) {
                    PriorityItem(priority = priority)
                }
            }
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllClicked: () -> Unit
) {
    var dropdownState by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { dropdownState = !dropdownState }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.more_options)
        )
        DropdownMenu(
            expanded = dropdownState,
            onDismissRequest = { dropdownState = !dropdownState }
        ) {
            DropdownMenuItem(onClick = {
                dropdownState = !dropdownState
                onDeleteAllClicked()
            }) {
                Text(
                    text = stringResource(R.string.delete_all),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ListAppBarPreview() {
    ToDoComposeTheme {
        ListAppBar()
    }
}