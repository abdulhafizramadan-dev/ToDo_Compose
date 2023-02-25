package com.ahr.todo_compose.ui.screen.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.todo_compose.R
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.ui.component.PriorityItem
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme
import com.ahr.todo_compose.ui.viewmodel.SharedViewModel
import com.ahr.todo_compose.util.SearchAppBarState
import com.ahr.todo_compose.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchQueryState: String,
    onQueryChanged: (String) -> Unit
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteAllClicked = {}
            )
        }
        else -> {
            SearchAppBar(
                query = searchQueryState,
                onQueryChanged = { newQuery ->
                    sharedViewModel.searchQueryState.value = newQuery
                    onQueryChanged(newQuery)
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                }
            )
        }
    }
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
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onCloseClicked: () -> Unit
) {

    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primarySurface
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = onQueryChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.alpha(ContentAlpha.medium),
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_tasks),
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    color = Color.White
                )
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    when (trailingIconState) {
                        TrailingIconState.READY_TO_DELETE -> {
                            onQueryChanged("")
                            trailingIconState = TrailingIconState.READY_TO_CLOSE
                        }
                        TrailingIconState.READY_TO_CLOSE -> {
                            if (query.isNotEmpty()) {
                                onQueryChanged("")
                            } else {
                                onCloseClicked()
                            }
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_search),
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {

            }),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
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
fun SearchAppBarPreview() {
    ToDoComposeTheme {
        SearchAppBar(
            query = "",
            onQueryChanged = {},
            onCloseClicked = {}
        )
    }
}
