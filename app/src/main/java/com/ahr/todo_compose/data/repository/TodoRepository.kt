package com.ahr.todo_compose.data.repository

import com.ahr.todo_compose.data.TodoDao
import com.ahr.todo_compose.data.model.TodoTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {

    val getAllTasks: Flow<List<TodoTask>> get() = todoDao.getAllTasks()
    val sortByLowPriority: Flow<List<TodoTask>> get() = todoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<TodoTask>> get() = todoDao.sortByHighPriority()

    fun searchTasks(searchQuery: String): Flow<List<TodoTask>> {
        return todoDao.searchTasks(searchQuery = searchQuery)
    }

    fun getSelectedTask(taskId: Int): Flow<TodoTask> {
        return todoDao.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(todoTask: TodoTask) {
        todoDao.addTask(todoTask)
    }

    suspend fun updateTask(todoTask: TodoTask) {
        todoDao.updateTask(todoTask)
    }

    suspend fun deleteTask(todoTask: TodoTask) {
        todoDao.deleteTask(todoTask)
    }

    suspend fun deleteAllTasks() {
        todoDao.deleteAllTasks()
    }
}