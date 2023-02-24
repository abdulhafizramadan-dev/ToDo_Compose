package com.ahr.todo_compose.ui.screen.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ahr.todo_compose.data.model.TodoTask
import com.ahr.todo_compose.ui.viewmodel.SharedViewModel
import com.ahr.todo_compose.util.Action

@Composable
fun TaskScreen(
    selectedTask: TodoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit,
) {

    var title by sharedViewModel.title
    var description by sharedViewModel.description
    var priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen,
            )
        }
    ) { paddingValues ->
        TaskContent(
            title = title,
            onTitleChanged = { title = it },
            description = description,
            onDescriptionChanged = { description = it },
            priority = priority,
            onPriorityChanged = { priority = it },
            modifier = Modifier.padding(paddingValues)
        )
    }
}