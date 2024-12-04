package com.example.lab08.ui.theme.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lab08.TaskViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel, onTaskAdded: () -> Unit, onCancel: () -> Unit) {
    var newTaskDescription by remember { mutableStateOf("") }
    var newTaskCategory by remember { mutableStateOf("") }
    var newTaskPriority by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = newTaskDescription,
            onValueChange = { newTaskDescription = it },
            label = { Text("Nueva tarea") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = newTaskCategory,
            onValueChange = { newTaskCategory = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Prioridad")
        Row {
            RadioButton(
                selected = newTaskPriority == 0,
                onClick = { newTaskPriority = 0 }
            )
            Text(text = "Baja", modifier = Modifier.padding(start = 8.dp))

            RadioButton(
                selected = newTaskPriority == 1,
                onClick = { newTaskPriority = 1 }
            )
            Text(text = "Media", modifier = Modifier.padding(start = 8.dp))

            RadioButton(
                selected = newTaskPriority == 2,
                onClick = { newTaskPriority = 2 }
            )
            Text(text = "Alta", modifier = Modifier.padding(start = 8.dp))
        }

        // Botón para agregar la tarea
        Button(
            onClick = {
                if (newTaskDescription.isNotEmpty()) {
                    viewModel.addTask(newTaskDescription, newTaskCategory, newTaskPriority)
                    onTaskAdded()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Agregar tarea")
        }

        // Botón para cancelar
        Button(
            onClick = {
                onCancel()  // Llamar a la función de cancelar
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Cancelar")
        }
    }
}
