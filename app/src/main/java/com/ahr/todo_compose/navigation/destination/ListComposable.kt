package com.ahr.todo_compose.navigation.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ahr.todo_compose.ui.screen.list.ListScreen
import com.ahr.todo_compose.ui.viewmodel.SharedViewModel
import com.ahr.todo_compose.util.Constants.LIST_ARGUMENT_KEY
import com.ahr.todo_compose.util.Constants.LIST_SCREEN
import com.ahr.todo_compose.util.toAction

fun NavGraphBuilder.listComposable(
    sharedViewModel: SharedViewModel,
    navigateToTaskScreen: (Int) -> Unit,
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(name = LIST_ARGUMENT_KEY) { type = NavType.StringType })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        ListScreen(
            sharedViewModel = sharedViewModel,
            navigateToTaskScreen = navigateToTaskScreen,
        )
    }
}