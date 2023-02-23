package com.ahr.todo_compose.ui.screen.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.todo_compose.R
import com.ahr.todo_compose.ui.theme.ToDoComposeTheme

@Composable
fun ListAppBar() {
    DefaultListAppBar()
}

@Composable
fun DefaultListAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ListAppBarPreview() {
    ToDoComposeTheme {
        ListAppBar()
    }
}