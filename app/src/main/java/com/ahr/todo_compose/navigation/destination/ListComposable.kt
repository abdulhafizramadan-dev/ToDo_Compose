package com.ahr.todo_compose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ahr.todo_compose.util.Constants.LIST_ARGUMENT_KEY
import com.ahr.todo_compose.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(name = LIST_ARGUMENT_KEY) { type = NavType.StringType })
    ) { navBackStackEntry ->

    }
}