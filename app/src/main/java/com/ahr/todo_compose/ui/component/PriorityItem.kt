package com.ahr.todo_compose.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun PriorityItem(
    priority: Priority,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(16.dp)) {
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name.lowercase().capitalize(Locale.current),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PriorityItemPreview() {
    ToDoComposeTheme {
        PriorityItem(priority = Priority.HIGH)
    }
}
