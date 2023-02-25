package com.ahr.todo_compose.ui.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ahr.todo_compose.R
import com.ahr.todo_compose.ui.viewmodel.SharedViewModel
import com.ahr.todo_compose.util.Action
import com.ahr.todo_compose.util.SearchAppBarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    sharedViewModel: SharedViewModel,
    navigateToTaskScreen: (Int) -> Unit,
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchQueryState: String by sharedViewModel.searchQueryState

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val action by sharedViewModel.action

    LaunchedEffect(key1 = Unit) {
        sharedViewModel.getAllTasks()
    }

    LaunchedEffect(key1 = searchAppBarState) {
        if (searchAppBarState == SearchAppBarState.CLOSED) {
            sharedViewModel.getAllTasks()
        }
    }
    
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        scope = scope,
        handleDatabaseAction = { sharedViewModel.handleDatabaseOperation(action) },
        taskTitle = sharedViewModel.title.value,
        action = action,
        onUndoClicked =  { sharedViewModel.action.value = it }
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchQueryState = searchQueryState,
                onQueryChanged = { query -> sharedViewModel.searchTasks(query) }
            )
         },
        floatingActionButton = {
            ListFab { navigateToTaskScreen(-1) }
        },
    ) { paddingValues ->
        ListContent(
            tasks = allTasks,
            navigateToTaskScreen = navigateToTaskScreen,
            modifier = Modifier.padding(paddingValues),
        )
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

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    handleDatabaseAction: ()-> Unit,
    taskTitle: String,
    action: Action,
    onUndoClicked: (Action) -> Unit
) {
    handleDatabaseAction()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = setActionLabel(action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun setActionLabel(action: Action): String {
    return when (action) {
        Action.DELETE,
        Action.DELETE_ALL -> "UNDO"
        else -> "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (
        action == Action.DELETE &&
        snackBarResult == SnackbarResult.ActionPerformed
    ) {
        onUndoClicked(Action.UNDO)
    }
}
