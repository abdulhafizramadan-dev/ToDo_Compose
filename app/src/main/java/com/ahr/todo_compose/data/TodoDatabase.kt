package com.ahr.todo_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahr.todo_compose.data.model.TodoTask

@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

}