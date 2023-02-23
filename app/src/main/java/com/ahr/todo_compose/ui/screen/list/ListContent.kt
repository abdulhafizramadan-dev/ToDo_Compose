package com.ahr.todo_compose.ui.screen.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.data.model.TodoTask
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme
import com.ahr.todo_compose.util.RequestState

@Composable
fun ListContent(
    tasks: RequestState<List<TodoTask>>,
    navigateToTaskScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (tasks is RequestState.Success) {
        if (tasks.data.isNotEmpty()) {
            DisplayTasks(
                tasks = tasks.data,
                navigateToTaskScreen = navigateToTaskScreen,
                modifier = modifier
            )
        } else {
            EmptyContent(modifier = modifier)
        }
    }
}

@Composable
fun DisplayTasks(
    tasks: List<TodoTask>,
    navigateToTaskScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = tasks, key = { it.id }) { todoTask ->
                TodoTaskItem(todoTask = todoTask, onTaskClicked = navigateToTaskScreen)
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoTaskItem(
    todoTask: TodoTask,
    onTaskClicked: (taskId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = { onTaskClicked(todoTask.id) }) {
        Column(modifier = modifier.padding(all = 16.dp)) {
            Row {
                Text(
                    text = todoTask.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Canvas(modifier = Modifier.size(16.dp)) {
                    drawCircle(todoTask.priority.color)
                }
            }
            Text(
                text = todoTask.description,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.paddingFromBaseline(top = 20.dp)
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    ToDoComposeTheme {
        Surface {
            TodoTaskItem(
                todoTask = TodoTask(
                    id = 1,
                    title = "Title",
                    description = "Some random text here...",
                    priority = Priority.HIGH
                ),
                onTaskClicked = {}
            )
        }
    }
}
