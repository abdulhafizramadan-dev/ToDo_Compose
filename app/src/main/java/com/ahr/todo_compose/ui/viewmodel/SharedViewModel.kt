package com.ahr.todo_compose.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.todo_compose.data.model.Priority
import com.ahr.todo_compose.data.model.TodoTask
import com.ahr.todo_compose.data.repository.TodoRepository
import com.ahr.todo_compose.util.Action
import com.ahr.todo_compose.util.RequestState
import com.ahr.todo_compose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val searchAppBarState = mutableStateOf(SearchAppBarState.CLOSED)
    val searchQueryState = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<TodoTask>>> get() = _allTasks

    val id = mutableStateOf(0)
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val priority = mutableStateOf(Priority.LOW)

    val action = mutableStateOf(Action.NO_ACTION)

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        viewModelScope.launch {
            repository.getAllTasks
                .catch { _allTasks.value = RequestState.Error(it) }
                .collect { _allTasks.value = RequestState.Success(it)}
        }
    }

    private val _selectedTask = MutableStateFlow<TodoTask?>(null)
    val selectedTask: StateFlow<TodoTask?> get() = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect { todoTask ->
                _selectedTask.value = todoTask
            }
        }
    }

    fun updateTaskFields(selectedTask: TodoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

    fun handleDatabaseOperation(action: Action) {
        when (action) {
            Action.ADD-> addTask()
            Action.UPDATE->{}
            Action.DELETE->{}
            Action.DELETE_ALL->{}
            Action.UNDO->{}
            else ->{}
        }
        this.action.value = Action.NO_ACTION
    }

    private fun addTask() {
        viewModelScope.launch {
            val todoTask = TodoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(todoTask)
        }
    }
}