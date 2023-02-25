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
    
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        scope = scope,
        handleDatabaseAction = { sharedViewModel.handleDatabaseOperation(action) },
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchQueryState = searchQueryState
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
    action: Action
) {
    handleDatabaseAction()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = "Ok"
                )
            }
        }
    }
}
