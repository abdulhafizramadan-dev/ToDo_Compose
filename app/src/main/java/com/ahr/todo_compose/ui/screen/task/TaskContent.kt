package com.ahr.todo_compose.ui.screen.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.todo_compose.R
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.ui.component.PriorityDropDown
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun TaskContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit,
    priority: Priority,
    onPriorityChanged: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChanged,
            label = { Text(text = stringResource(R.string.title)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        PriorityDropDown(
            priority = priority,
            onPriorityChanged = onPriorityChanged,
            dropdownModifier = Modifier.padding(horizontal = 16.dp)
        )
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChanged,
            label = { Text(text = stringResource(R.string.description)) },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun TaskContentPreview() {
    ToDoComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TaskContent(
                title = "",
                onTitleChanged = {},
                description = "",
                onDescriptionChanged = {},
                priority = Priority.LOW,
                onPriorityChanged = {}
            )
        }
    }
}
