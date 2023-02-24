package com.ahr.todo_compose.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPriorityChanged: (Priority) -> Unit,
    modifier: Modifier = Modifier,
    dropdownModifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }
    val animatedAngle by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            )
            .clickable { expanded = !expanded }
    ) {
        Canvas(modifier = Modifier
            .size(16.dp)
            .weight(2f)
        ) {
            drawCircle(priority.color)
        }
        Text(
            text = priority.name,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.weight(8f)
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier
                .rotate(animatedAngle)
                .weight(2f)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = !expanded },
            modifier = dropdownModifier.fillMaxWidth(0.92f)
        ) {
            val priorities = Priority.values()
            priorities.forEach { priority ->
                DropdownMenuItem(onClick = {
                    expanded = !expanded
                    onPriorityChanged(priority)
                }) {
                    PriorityItem(priority = priority)
                }
            }
        }
    }
}

@Preview
@Composable
fun PriorityDropDownPreview() {
    ToDoComposeTheme {
        Surface {
            var priority by remember {
                mutableStateOf(Priority.LOW)
            }
            PriorityDropDown(priority = priority, onPriorityChanged = { priority = it })
        }
    }
}
