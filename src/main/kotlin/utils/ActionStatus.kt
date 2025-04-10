package utils

sealed class ActionStatus<out T> {
    data class Success<out T>(val data: T) : ActionStatus<T>()
    data class Error(val errorMessage: String) : ActionStatus<Nothing>()
    object InProgress : ActionStatus<Nothing>()
    object Empty : ActionStatus<Nothing>()
}
