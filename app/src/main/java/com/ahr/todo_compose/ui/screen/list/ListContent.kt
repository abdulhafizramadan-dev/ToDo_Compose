package com.ahr.todo_compose.ui.screen.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.data.model.TodoTask
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun ListContent(modifier: Modifier = Modifier) {

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
                    style = MaterialTheme.typography.subtitle1,
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
