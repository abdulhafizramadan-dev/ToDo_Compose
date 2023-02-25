package com.ahr.todo_compose.ui.screen.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ahr.todo_compose.R
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
    
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { priority ->
                    if (priority == Action.NO_ACTION) {
                        navigateToListScreen(priority)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(priority)
                        } else {
                            showToast(context)
                        }
                    }
                },
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

fun showToast(context: Context) {
    Toast.makeText(
        context,
        context.getString(R.string.empty_fields),
        Toast.LENGTH_SHORT
    ).show()
}
