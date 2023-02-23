package com.ahr.todo_compose.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.todo_compose.data.model.TodoTask
import com.ahr.todo_compose.data.repository.TodoRepository
import com.ahr.todo_compose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val searchAppBarState = mutableStateOf(SearchAppBarState.CLOSED)
    val searchQueryState = mutableStateOf("")

    private val _allTasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val allTasks: StateFlow<List<TodoTask>> get() = _allTasks

    fun getAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks.collect { allTasks ->
                _allTasks.value = allTasks
            }
        }
    }

}