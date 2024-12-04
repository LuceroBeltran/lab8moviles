package com.example.lab08.ui.theme.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddNoteFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,  // El FAB ahora abre el Bottom Sheet
        containerColor = Color(0xFF7D5260)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(Icons.Default.Add, contentDescription = "Agregar Nota")
            Text(text = "Agregar nota")
        }
    }
}
