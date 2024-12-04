package com.example.lab08.ui.theme.Content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab08.TaskViewModel

@Composable
fun NavigationHost(navController: NavHostController, padding: PaddingValues,viewModel: TaskViewModel) {
    NavHost(navController, startDestination = "lista") {
        composable("lista") {
            NoteContent(padding = padding, viewModel = viewModel,navController = navController)  // Asegúrate de pasar el viewModel
        }
        composable("configuracion") {
            SettingsScreen(padding = padding)
        }
        composable("editTask/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            taskId?.let { id ->
                EditTaskScreen(navController = navController, viewModel = viewModel, taskId = id, padding = padding)
            }
        }

    }
}
