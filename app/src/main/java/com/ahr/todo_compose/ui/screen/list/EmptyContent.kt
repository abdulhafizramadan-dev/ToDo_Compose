package com.ahr.todo_compose.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.todo_compose.R
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun EmptyContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.SentimentDissatisfied,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .size(120.dp)
                .alpha(ContentAlpha.disabled)
        )
        Text(
            text = stringResource(R.string.no_tasks_found),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.alpha(ContentAlpha.disabled)
        )
    }
}

@Preview
@Composable
fun EmptyContentPreview() {
    ToDoComposeTheme {
        Surface {
            EmptyContent()
        }
    }
}
