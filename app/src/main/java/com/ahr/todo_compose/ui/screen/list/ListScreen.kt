package com.ahr.todo_compose.ui.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ahr.todo_compose.R
import com.ahr.todo_compose.ui.viewmodel.SharedViewModel
import com.ahr.todo_compose.util.SearchAppBarState

@Composable
fun ListScreen(
    sharedViewModel: SharedViewModel,
    navigateToTaskScreen: (Int) -> Unit,
) {

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchQueryState: String by sharedViewModel.searchQueryState

    val allTasks by sharedViewModel.allTasks.collectAsState()

    LaunchedEffect(key1 = Unit) {
        sharedViewModel.getAllTasks()
    }

    Scaffold(
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
