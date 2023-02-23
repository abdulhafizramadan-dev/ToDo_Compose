package com.ahr.todo_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ahr.todo_compose.navigation.destination.listComposable
import com.ahr.todo_compose.navigation.destination.taskComposable
import com.ahr.todo_compose.ui.viewmodel.SharedViewModel
import com.ahr.todo_compose.util.Constants.LIST_SCREEN

@Composable
fun TodoNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val navigator = remember(navController) {
        Navigator(navController)
    }
    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            sharedViewModel = sharedViewModel,
            navigateToTaskScreen = navigator.toTaskScreen,
        )
        taskComposable(
            navigateToListScreen = navigator.toListScreen
        )
    }
}