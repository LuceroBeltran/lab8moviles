package com.example.lab08.ui.theme.Content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lab08.TaskViewModel

@Composable
fun EditTaskScreen(
    navController: NavHostController,
    viewModel: TaskViewModel,
    taskId: Int,
    padding: PaddingValues
) {
    // Usamos collectAsState para obtener la tarea desde el ViewModel en tiempo real
    val task = viewModel.getTaskById(taskId).collectAsState(null).value

    // Variables para los campos editables, inicializadas cuando la tarea esté disponible
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(0) }
    var category by remember { mutableStateOf("") }

    // Esto se ejecuta cuando el task cambia (cuando se obtienen los datos de la base de datos)
    LaunchedEffect(task) {
        task?.let {
            description = it.description
            priority = it.priority
            category = it.category
        }
    }

    // Si la tarea es nula, mostrar un indicador de carga
    if (task == null) {
        CircularProgressIndicator(modifier = Modifier.padding(padding))
    } else {
        // Mostrar la pantalla de edición cuando los datos de la tarea estén disponibles
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Campo para editar la descripción de la tarea
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción de la Tarea") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Campo para editar la categoría de la tarea
            TextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Categoría") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // RadioButtons para editar la prioridad
            Text("Prioridad", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RadioButton(
                    selected = priority == 0,
                    onClick = { priority = 0 }
                )
                Text(text = "Baja", modifier = Modifier.padding(start = 8.dp))

                RadioButton(
                    selected = priority == 1,
                    onClick = { priority = 1 }
                )
                Text(text = "Media", modifier = Modifier.padding(start = 8.dp))

                RadioButton(
                    selected = priority == 2,
                    onClick = { priority = 2 }
                )
                Text(text = "Alta", modifier = Modifier.padding(start = 8.dp))
            }

            // Botón para guardar los cambios
            Button(
                onClick = {
                    if (task != null) {
                        viewModel.updateTask(
                            task.copy(
                                description = description,
                                category = category,
                                priority = priority
                            )
                        )
                        navController.popBackStack() // Volver a la pantalla anterior
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Guardar Cambios")
            }

            // Botón para cancelar la edición
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Cancelar")
            }
        }
    }
}


