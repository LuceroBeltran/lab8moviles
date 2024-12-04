package com.example.lab08

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 3)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun taskDao() : TaskDao
}