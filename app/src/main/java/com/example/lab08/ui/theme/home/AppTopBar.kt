package com.example.lab08.ui.theme.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.lab08.TaskViewModel
import androidx.compose.material3.ColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavHostController, viewModel: TaskViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isSearching) {
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.searchTasks(searchQuery)  // Actualizar la lista de tareas
                    },
                    placeholder = { Text(text = "Buscar tareas...") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    )
                )
            } else {
                Text(
                    text = "Listado de Tareas",
                    color = Color.White, // Texto en color blanco
                    style = MaterialTheme.typography.titleLarge, // Estilo de texto
                    modifier = Modifier.clickable {
                        navController.navigate("lista") // Navegar de vuelta a la página de inicio
                    }
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF7D5260),  // Color de fondo
            titleContentColor = Color.White, // Color del texto del título
            actionIconContentColor = Color.White // Color de los íconos
        ),
        actions = {
            if (!isSearching) {
                IconButton(onClick = { isSearching = true }) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar tareas")
                }

                // Icono de notificaciones
                IconButton(onClick = {
                    // Aquí puedes definir la acción al hacer clic en el icono de notificaciones
                }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                }

            } else {
                IconButton(onClick = {
                    searchQuery = ""
                    isSearching = false
                    viewModel.searchTasks("")  // Restaurar lista completa al cerrar búsqueda
                }) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar búsqueda")
                }
            }
        }
    )
}

