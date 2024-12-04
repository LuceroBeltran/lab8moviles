package com.example.lab08

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TaskDao {


    // Obtener todas las tareas
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>


    // Insertar una nueva tarea
    @Insert
    suspend fun insertTask(task: Task)

    // Obtener tareas por categoría
    @Query("SELECT * FROM tasks WHERE category = :category")
    suspend fun getTasksByCategory(category: String): List<Task>

    // Marcar una tarea como completada o no completada
    @Update
    suspend fun updateTask(task: Task)


    // Eliminar todas las tareas
    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM tasks ORDER BY priority DESC")
    suspend fun getTasksOrderedByPriority(): List<Task>

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Int): Task?
}
