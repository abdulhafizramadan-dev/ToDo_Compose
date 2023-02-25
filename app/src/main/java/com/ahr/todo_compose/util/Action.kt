package com.ahr.todo_compose.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION;
}

fun String?.toAction(): Action {
    return try {
        Action.valueOf(this ?: Action.NO_ACTION.name)
    } catch (_: Exception) {
        Action.NO_ACTION
    }
}
