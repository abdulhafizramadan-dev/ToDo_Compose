package com.ahr.todo_compose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ahr.todo_compose.ui.screen.task.TaskScreen
import com.ahr.todo_compose.util.Action
import com.ahr.todo_compose.util.Constants.TASK_ARGUMENT_KEY
import com.ahr.todo_compose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(name = TASK_ARGUMENT_KEY) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY) ?: -1
        TaskScreen(
            taskId = taskId,
            navigateToListScreen = navigateToListScreen,
        )
    }
}