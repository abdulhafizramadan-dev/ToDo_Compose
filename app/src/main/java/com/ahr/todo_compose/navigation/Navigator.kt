package com.ahr.todo_compose.navigation

import androidx.navigation.NavHostController
import com.ahr.todo_compose.util.Action
import com.ahr.todo_compose.util.Constants.LIST_SCREEN

class Navigator(private val navController: NavHostController) {

    val toListScreen: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val toTaskScreen: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }

}