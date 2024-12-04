package com.example.lab08.ui.theme.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lab08.TaskViewModel
import com.example.lab08.ui.theme.filter.FilterBottomSheet
import java.util.*


@Composable
fun AppBottomBar(navController: NavHostController,viewModel: TaskViewModel) {
    var showFilterSheet by remember { mutableStateOf(false) }

    BottomAppBar(
        content = {
            // Ordenar
            BottomBarItem(icon = Icons.Default.Sort, label = "Ordenar") {
                // Acción de ordenar
                viewModel.getTasksOrderedByPriority()
            }

            Spacer(Modifier.weight(1f, true))

            // Filtrar
            BottomBarItem(icon = Icons.Default.FilterList, label = "Filtrar") {
                showFilterSheet = true
            }

            Spacer(Modifier.weight(1f, true))

            // Compartir
            BottomBarItem(icon = Icons.Default.Share, label = "Compartir") {
                // Acción de compartir
            }

            Spacer(Modifier.weight(1f, true))

            // Configuración
            BottomBarItem(
                icon = Icons.Default.Settings, label = "Config.",
                onClick = { navController.navigate("configuracion") }
            )
        }
    )

    if (showFilterSheet) {
        FilterBottomSheet(
            onDismiss = { showFilterSheet = false },
            onApplyFilters = { status, category, priority ->
                viewModel.filterTasks(status, category, priority)
                showFilterSheet = false
            },
            viewModel = viewModel  // Asegúrate de pasar el viewModel
        )
    }

}

@Composable
fun BottomBarItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(icon, contentDescription = label)
        }
        Text(text = label, style = MaterialTheme.typography.labelSmall)
    }
}

