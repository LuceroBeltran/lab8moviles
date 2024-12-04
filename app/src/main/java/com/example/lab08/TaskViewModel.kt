package com.example.lab08

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: TaskDao): ViewModel(){
    // Estado para la lista de tareas
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks


    init {
        // Al inicializar, cargamos las tareas de la base de datos
        viewModelScope.launch {
            _tasks.value = dao.getAllTasks()
        }
    }


    // Función para añadir una nueva tarea
    fun addTask(description: String, category: String, priority: Int) {
        val newTask = Task(description = description,category = category, priority = priority)
        viewModelScope.launch {
            dao.insertTask(newTask)
            _tasks.value = dao.getAllTasks() // Recargamos la lista
        }
    }


    // Función para alternar el estado de completado de una tarea
    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(isCompleted = !task.isCompleted)
            dao.updateTask(updatedTask)
            _tasks.value = dao.getAllTasks() // Recargamos la lista
        }
    }


    // Función para eliminar todas las tareas
    fun deleteAllTasks() {
        viewModelScope.launch {
            dao.deleteAllTasks()
            _tasks.value = emptyList() // Vaciamos la lista en el estado
        }
    }

    // Función para filtrar tareas
    fun filterTasks(status: String, category: String, priority: Int) {
        viewModelScope.launch {
            val filteredTasks = dao.getAllTasks().filter { task ->
                (status == "" || task.isCompleted == (status == "completa")) &&
                        (category == "" || task.category == category) &&
                        (priority == -1 || task.priority == priority)
            }
            _tasks.value = filteredTasks
        }
    }
    fun getAllTasks() {
        viewModelScope.launch {
            _tasks.value = dao.getAllTasks()
        }
    }

    fun getTasksOrderedByPriority() {
        viewModelScope.launch {
            _tasks.value = dao.getTasksOrderedByPriority()
        }
    }

    // Función para buscar tareas por palabras clave
    fun searchTasks(query: String) {
        viewModelScope.launch {
            val searchResults = if (query.isNotEmpty()) {
                dao.getAllTasks().filter { task ->
                    task.description.contains(query, ignoreCase = true) ||
                            task.category.contains(query, ignoreCase = true)
                }
            } else {
                dao.getAllTasks()
            }
            _tasks.value = searchResults
        }
    }

    // Función para eliminar una tarea
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            dao.deleteTask(task)
            _tasks.value = dao.getAllTasks()  // Recargamos la lista después de eliminar
        }
    }

    fun getTaskById(taskId: Int): Flow<Task?> {
        return flow {
            emit(dao.getTaskById(taskId))
        }
    }

    // Actualizar una tarea existente
    fun updateTask(task: Task) {
        viewModelScope.launch {
            dao.updateTask(task)
            _tasks.value = dao.getAllTasks() // Recargar la lista
        }
    }
}