package com.example.lab08.ui.theme.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.rememberNavController
import com.example.lab08.TaskViewModel
import com.example.lab08.ui.theme.Content.NavigationHost
import com.example.lab08.ui.theme.Content.NoteContent
import com.example.lab08.ui.theme.create.TaskScreen
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteAppScaffold(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppTopBar(navController = navController, viewModel = viewModel) },
        floatingActionButton = { AddNoteFab(onClick = { showDialog = true }) },
        content = { padding ->
            NavigationHost(navController = navController, padding = padding, viewModel = viewModel)
            if (showDialog) {
                Dialog(
                    onDismissRequest = { showDialog = false },
                    properties = DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        shape = MaterialTheme.shapes.large
                    ) {
                        TaskScreen(
                            viewModel = viewModel,
                            onTaskAdded = { showDialog = false },
                            onCancel = { showDialog = false }  // Aquí se cierra el diálogo cuando se cancela
                        )
                    }
                }
            }
        },
        bottomBar = { AppBottomBar(navController = navController, viewModel = viewModel) }
    )
}









